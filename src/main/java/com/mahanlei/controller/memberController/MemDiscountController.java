package com.mahanlei.controller.memberController;

import com.mahanlei.Util.Message;
import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.model.DiscountCoupon;
import com.mahanlei.service.MemberService;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="http://localhost:8081")//允许跨域请求
public class MemDiscountController {
    MemberService memberService= ServiceFactory.getMemberService();
    @RequestMapping(value = "/discountCoupon",method= RequestMethod.POST)
    public JSONObject getDisInfo(@RequestParam("mid") String mid){
        JSONObject jsonObject=new JSONObject();
        DiscountCoupon discountCoupon=memberService.getDisInfo(mid);
        jsonObject.put("discountCouponType1",discountCoupon.getCount1());
        jsonObject.put("discountCouponType2",discountCoupon.getCount2());
        jsonObject.put("discountCouponType3",discountCoupon.getCount3());
        jsonObject.put("discountCouponType4",discountCoupon.getCount4());
        return jsonObject;
    }
@RequestMapping(value = "/discountCoupon/exchange",method = RequestMethod.POST)
    public JSONObject exchangeDis(@RequestParam( "mid") String mid,@RequestParam("discountCouponType1") int discountCouponType1,
                                  @RequestParam("discountCouponType2") int discountCouponType2,
                                  @RequestParam("discountCouponType3") int discountCouponType3,
                                  @RequestParam("discountCouponType4") int discountCouponType4){
        int points=memberService.getMemberInfo(mid).getPoints();
        boolean able=false;
        Message message=null;
        if(discountCouponType1==1){
            if(points>=200){
                able=true;
            }
        }
    if(discountCouponType2==1){
        if(points>=300){
            able=true;
        }
    }
    if(discountCouponType3==1){
        if(points>=400){
            able=true;
        }
    }
    if(discountCouponType4==1){
        if(points>=500){
            able=true;
        }
    }
    JSONObject jsonObject = new JSONObject();
    if(able) {
        message = memberService.exchangeDis(new DiscountCoupon(mid, discountCouponType1,
                discountCouponType2, discountCouponType3, discountCouponType4));


        if (message.equals(Message.EXCHANGE_DISCOUNT_SUCCESS)) {
            jsonObject.put("code", "200");
            jsonObject.put("msg", "成功兑换优惠券,请刷新界面");

        } else {
            jsonObject.put("code", "500");
            jsonObject.put("msg", "兑换失败，请再次尝试");
        }
    }else {
        jsonObject.put("code", "500");
        jsonObject.put("msg", "积分值不足，不能兑换");
    }
return jsonObject;
}
}
