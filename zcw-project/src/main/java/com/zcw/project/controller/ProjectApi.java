package com.zcw.project.controller;

import com.zcw.common.base.BaseApiService;
import com.zcw.common.base.BaseResponse;
import com.zcw.project.Srevice.ProjectService;
import com.zcw.project.vo.publish.BasePublishVO;
import com.zcw.project.vo.publish.ProjectInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectApi extends BaseApiService {

    @Autowired
    ProjectService projectService;

    @RequestMapping("/ProjectPublish")
    public BaseResponse Publish(BasePublishVO vo){
        BaseResponse baseResponse = projectService.PublishService(vo);

        return baseResponse;


        @RequestMapping("/ProjectInfo")
        public BaseResponse ProjectInfo(ProjectInfoVo projectInfovo){

            return baseResponse;


    }


//第三步   ...



//第四步 保存项目

        @RequestMapping("/SaveProject")
        public BaseResponse SaveProject(String accessToken,String projectToken,String status){

            return baseResponse;


        }
}
