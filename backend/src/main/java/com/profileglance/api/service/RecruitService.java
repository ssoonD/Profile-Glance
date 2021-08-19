package com.profileglance.api.service;

import com.profileglance.api.request.RecruitPostReq;
import com.profileglance.api.response.RecruitPostRes;
import com.profileglance.db.entity.Recruit;

import java.util.List;

public interface RecruitService {

    Boolean uploadRecruit(RecruitPostReq recruitPostReq);

    List<RecruitPostRes> searchByCompanyName(String companyName);

    List<RecruitPostRes> searchByJobName(String jobName);

    List<RecruitPostRes> searchAll();

    Boolean deleteRecruit(Long recruitId);
}