package com.zcw.project.Srevice;

import com.zcw.common.base.BaseResponse;
import com.zcw.project.vo.publish.BasePublishVO;
import com.zcw.project.vo.publish.ProjectInfoVo;
import com.zcw.project.vo.publish.TReturnVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {


    BaseResponse PublishService(BasePublishVO vo);

    BaseResponse ProjectInfoService(ProjectInfoVo projectInfoVo);


    BaseResponse ProjectReturnService(List<TReturnVo> TReturnVo);

    BaseResponse SaveProject(String accessToken,String projectToken,String status);



    }
