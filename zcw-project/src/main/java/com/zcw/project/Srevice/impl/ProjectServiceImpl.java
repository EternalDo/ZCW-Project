package com.zcw.project.Srevice.impl;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zcw.common.base.BaseApiService;
import com.zcw.common.base.BaseResponse;
import com.zcw.project.Srevice.ProjectService;
import com.zcw.project.bean.TReturn;
import com.zcw.project.util.RedisUtil;
import com.zcw.project.vo.publish.BasePublishVO;
import com.zcw.project.vo.publish.ProjectInfoVo;
import com.zcw.project.vo.publish.TReturnVo;
import com.zcw.project.vo.publish.PublicPublishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectServiceImpl extends BaseApiService implements ProjectService {
private  final String PROJECT ="project—provisionalToken";

    @Autowired
    RedisUtil redisUtil;

    @Override
    public BaseResponse PublishService(BasePublishVO vo) {

        String accessToken = vo.getAccessToken();

        if (accessToken.isEmpty()){ setResultError("用户token为空");}

        String access = redisUtil.getString(accessToken);

        if (access.isEmpty()){ setResultSuccess("请先登录");}


        //初始化大vo
        PublicPublishVO publishVO = new PublicPublishVO();

        int i = RandomUtil.randomInt();
        String projecrToken = PROJECT+i;

        //初始化项目token
        publishVO.setProjectToken(projecrToken);

        BeanUtils.copyProperties(vo, publishVO);
        String jsonpublshvo = JSON.toJSONString(publishVO);

        redisUtil.setString(projecrToken, jsonpublshvo);

        return setResultSuccess(jsonpublshvo);
        }


        //项目详情信息
    @Override
    public BaseResponse ProjectInfoService(ProjectInfoVo projectInfoVo) {

//        1 验证信息
        String accessToken = projectInfoVo.getAccessToken();

        if (accessToken.isEmpty()){ setResultError("用户token为空");}

        String access = redisUtil.getString(accessToken);

        if (access.isEmpty()){ setResultSuccess("请先登录");}


    //     2 从redis里获取大vo数据  讲此小vo封装到大VO
        //项目token 一般由前端自带传过来

        String string = redisUtil.getString(projectInfoVo.getProjectToken());
        JSONObject jsonObject = JSON.parseObject(string);
        PublicPublishVO publicPublishvo = jsonObject.toJavaObject(PublicPublishVO.class);
             //小vo扔到大vo
        BeanUtils.copyProperties(projectInfoVo, publicPublishvo);


        //新的大vo在放回去
        String JSONpublicPublishvo = JSON.toJSONString(publicPublishvo);

        redisUtil.setString(projectInfoVo.getProjectToken(), JSONpublicPublishvo);

        return setResultSuccess(publicPublishvo );
    }


//3   项目回报
@Override
public BaseResponse ProjectReturnService(List<TReturnVo> tReturnVoList) {

//    1 验证信息
    String accessToken = tReturnVoList.get(0).getAccessToken();

    if (accessToken.isEmpty()) {
        setResultError("用户token为空");
    }

    String access = redisUtil.getString(accessToken);

    if (access.isEmpty()) {
        setResultSuccess("请先登录");
    }


    //获取大vo
    String string = redisUtil.getString(tReturnVoList.get(0).getProjectToken());
    JSONObject jsonObject = JSON.parseObject(string);
    PublicPublishVO publicPublishvo = jsonObject.toJavaObject(PublicPublishVO.class);


    //大vo里的集合拿出来,做瓶子 存东西
    List<TReturn> projectReturns = publicPublishvo.getTReturnList();

    //遍历对象集合 一个一个的拿出来
    for (TReturnVo tReturnVo : tReturnVoList) {
        TReturn tReturn = new TReturn();

         //对象数据 扔到新对象里，再扔到大vo的对象集合里
        BeanUtils.copyProperties(tReturnVo, tReturn);
        projectReturns.add(tReturn);
    }
    //新的大vo保存有东西的集合数据
    publicPublishvo.setTReturnList(projectReturns);

    //新的大vo在放回redis去
    String JSONpublicPublishvo = JSON.toJSONString(publicPublishvo);

    redisUtil.setString(publicPublishvo.getProjectToken(), JSONpublicPublishvo);

    return setResultSuccess(publicPublishvo );

}




    //4 保存项目
    @Override
    public BaseResponse SaveProject(String accessToken, String projectToken, String status) {

         /* 就是将不断存储数据的最终大VO 拿出来
         *  然后创建一个个相应的对象 保存大vo的数据
         *  数据库操作 save保存。
         * */







        return null;
    }


}
