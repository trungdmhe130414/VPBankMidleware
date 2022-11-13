package com.example.vpbank.service.implService;

import com.example.vpbank.job.JobFTVan;
import com.example.vpbank.model.ObjectUpdate;
import com.example.vpbank.model.VanInvColl;
import com.example.vpbank.model.VanTVanMsgInDtl;
import com.example.vpbank.repository.VanInvCollRepository;
import com.example.vpbank.service.VanInvCollService;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/*
    Handle table VanInvColl(update status_CQT)
 */
@Service
public class VanInvCollServiceImpl implements VanInvCollService {

    @Autowired
    public VanInvCollRepository vanInvCollRepository;

    @Setter(onMethod = @__({@Autowired, @Qualifier("dataSource")}))
    private DataSource dataSource;

    private static final Logger logger = LogManager.getLogger(JobFTVan.class);
    @Override
    public void updateStatusCqt(List<VanTVanMsgInDtl> listProcess) {
        List<ObjectUpdate> objectUpdateList = new ArrayList<>();
        try {
            listProcess.stream().parallel().forEach(
                    e -> {
                        String infoType = e.getTypeNotice();
                        if(infoType == "14"){
                            String tranCode = e.getTransId();
                            VanInvColl vanInvColl = vanInvCollRepository.getAllIdByTranCode(tranCode);
                            // update status_CQT thanh 12
                            int id = vanInvColl.getId();
                            String SqlUpdate = "UPDATE VANP_INV SET STATUS_CQT = 12 WHERE bth_id = " + id;
                            JdbcOperations jdbcOperations = new JdbcTemplate(dataSource);
                            int rs = jdbcOperations.update(SqlUpdate);

                            // update status_CQT thanh 13
                            String content = e.getContentNotice();
                            JSONObject object = new JSONObject(content);
                            JSONObject arrError = object.getJSONObject("tbaoKTraDLieu");
                            JSONArray arr = arrError.getJSONArray("dsachHDonBTHLoi");
                            for(int i = 0 ; i < arr.length() ; i ++){
                                ObjectUpdate data = new ObjectUpdate();
                                DecimalFormat formatDecimal = new DecimalFormat("0000000");
                                int invNumber = Integer.parseInt(arr.getJSONObject(i).getString("soHDon"));
                                data.setInvNumber(formatDecimal.format(invNumber));
                                data.setTemplateCode(arr.getJSONObject(i).getString("khieuMauHDon"));
                                data.setInvSeries(arr.getJSONObject(i).getString("khieuHDon"));
                                objectUpdateList.add(data);
                            }
                        }
                        try {
                            String queryUpdateBatch = "UPDATE VANP_INV SET STATUS_CQT = 13 WHERE INV_SERIES = ? and INV_NUMBER = ? and TEMPLATE_CODE = ?";
                            JdbcOperations jdbcOperations = new JdbcTemplate(dataSource);
                            int effected = jdbcOperations.update(con -> {
                                PreparedStatement ps = con.prepareStatement(queryUpdateBatch);
                                for (ObjectUpdate objectUpdate : objectUpdateList){
                                    ps.setString(1, objectUpdate.getInvSeries());
                                    ps.setString(2, objectUpdate.getInvNumber());
                                    ps.setString(3, objectUpdate.getTemplateCode());
                                    ps.addBatch();
                                }
                                ps.executeBatch();
                                return ps;
                            });
                        }catch (Exception ex){
                            logger.info("Update VANP_INV error : " + ex);
                        }
                    }
            );
        }catch (Exception e){
            logger.info(e.getMessage());
        }

        }
    }

