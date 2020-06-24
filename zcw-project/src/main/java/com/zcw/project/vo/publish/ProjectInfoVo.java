package com.zcw.project.vo.publish;

import com.zcw.project.bean.TReturn;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProjectInfoVo extends BasePublishVO{

    private String projectToken;// 项目的临时token

    private List<Integer> typeids= new ArrayList<Integer>(); //项目的分类id
    private List<Integer> tagids= new ArrayList<Integer>(); //项目的标签id

    private String name;
    private String remark;
    private Long money;
    private Integer day;

    private String headerImage;//项目头部图片
    private List<String> detailsImage = new ArrayList<String>();//项目详情图片

    //发起人信息：自我介绍，详细自我介绍，联系电话，客服电话

    private List<TReturn> projectReturns = new ArrayList<TReturn>();//项目回报

}
