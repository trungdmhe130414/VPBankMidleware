package com.example.vpbank.service.implService;

import com.example.vpbank.model.VanpCgRefCodesFlag;
import com.example.vpbank.repository.VanCgRefCodesFlagRepository;
import com.example.vpbank.service.VanCgRefCodesFlagService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;

@Service
public class VanCgRefCodesFlagServiceImpl implements VanCgRefCodesFlagService {
    @Autowired
    public VanCgRefCodesFlagRepository vanCgRefCodesFlagRepository;

    @Setter(onMethod = @__({@Autowired, @Qualifier("dataSource")}))
    private DataSource dataSource;
    @Override
    public VanpCgRefCodesFlag getJob(int id) {
        try {
            VanpCgRefCodesFlag job = vanCgRefCodesFlagRepository.getJobById(id);
            return job;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void updateStatusJob(int id, String status) {
        String queryUpdateJob = "Update VANP_CG_REF_CODES_FLAG SET STATUS_JOB = '" + status+ "' WHERE  id = " + id;
        JdbcOperations jdbcOperations = new JdbcTemplate(dataSource);
        int eff = jdbcOperations.update(queryUpdateJob);
    }
}
