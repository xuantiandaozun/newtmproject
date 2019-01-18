package com.system.tianmayunxi.zp02yx_xzmbh.ui.integral;

import android.graphics.Color;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.system.myproject.base.MVPBaseFragment;
import com.system.myproject.base.MVPBasePresenter;
import com.system.tianmayunxi.zp02yx_xzmbh.R;
import com.system.tianmayunxi.zp02yx_xzmbh.R2;
import com.system.tianmayunxi.zp02yx_xzmbh.TmyxRouterConfig;
import com.system.uilibrary.views.titlebar.TitleBarView;
import com.tenma.ventures.bean.utils.TMSharedPUtil;

import butterknife.BindView;

@Route(path = TmyxRouterConfig.TMYX02_SIGNRULE)
public class SignRuleFragment extends MVPBaseFragment {
    @BindView(R2.id.titleBar)
    TitleBarView titleBar;
    @BindView(R2.id.tv_content)
    TextView tv_content;
    private int themeColor;
    private int textcolor;

    @Override
    protected MVPBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_signrule_zp01yx_bwusb2;
    }

    @Override
    protected void initDatas() {
        themeColor = Color.parseColor(TMSharedPUtil.getTMThemeColor(getActivity()));
        textcolor = Color.parseColor(TMSharedPUtil.getTMTitleTextColor(getActivity()));
        titleBar.setBackgroundColor(themeColor);
        titleBar.setTitleMainTextColor(textcolor);

        titleBar.setTitleMainText("签到规则")
                .setLeftTextDrawable(R.mipmap.icon_nav_back)
                .setOnLeftTextClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop();
                    }
                });
        tv_content.setText(Html.fromHtml("<p>\n" +
                "\t<span class=\"prop-font-content\">1. 每日24点前完成签到, 可连续获得累加积分.<br />\n" +
                "</span>\n" +
                "</p>\n" +
                "<p>\n" +
                "\t<span class=\"prop-font-content\">2. 连续签到规则: 签到第一天可获得2积分、第二天可</span>\n" +
                "</p>\n" +
                "<p>\n" +
                "\t<span class=\"prop-font-content\">&nbsp;&nbsp;&nbsp; 获得4积分, 以此类推, 第五天可获得10积分.</span>\n" +
                "</p>\n" +
                "<p>\n" +
                "\t<span class=\"prop-font-content\">3. 签到五天为一周期, 从连续第六天起, 后续每天不间\n" +
                "断</span>\n" +
                "</p>\n" +
                "<p>\n" +
                "\t<span class=\"prop-font-content\">&nbsp;&nbsp;&nbsp; 签到均可获得10积分.</span>\n" +
                "</p>\n" +
                "<p>\n" +
                "\t<span class=\"prop-font-content\">4. 额外奖励: 连续签到7天为一周期, 第7天签到奖励翻 <br />\n" +
                "</span>\n" +
                "</p>\n" +
                "<p>\n" +
                "\t<span class=\"prop-font-content\">&nbsp;&nbsp;&nbsp; 倍.&nbsp;</span>\n" +
                "</p>\n" +
                "<p>\n" +
                "\t<span class=\"prop-font-content\">5. 若连续签到中断, 签到将重新计算, 不可补签. </span><br />\n" +
                "<span class=\"prop-font-content\"></span>\n" +
                "</p>"));
    }
}
