package thKaguyaMod.entity.spellcard;

import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import thKaguyaMod.THShotLib;

import static thKaguyaMod.DanmakuConstants.FORM_CRYSTAL;
import static thKaguyaMod.THShotLib.SIZE;

public class THSC_HoukaKenran extends THSpellCard
{
	//華符「芳華絢爛」

	public THSC_HoukaKenran()
	{
		this.setNeedLevel(2);
		this.setRemoveTime(60);
		this.setEndTime(170);
		this.setOriginalUserName(MEIRIN);
	}
	
	@Override
	public void spellcard_main()
	{
    	if(time < 150 && time % 3 == 0)
    	{
    		Vec3 angle = user.getLookVec();
    		if(target.getHealth() > 0F)
    		{
    			angle = angle_UserToTarget();
    		}
    		//angle = THShotLib.getVecFromAngle(card.rotationYaw, card.rotationPitch);
    		THShotLib.createRingShot(user, pos_User(), angle, 0.4D, shot(THShotLib.FORM_CRYSTAL, THShotLib.YELLOW, SIZE[FORM_CRYSTAL], 5.0F), 6, 0.6D, 45.0F + MathHelper.sin(time / 20F) * 40F, (float)(time * 2F));
    		THShotLib.createRingShot(user, pos_User(), angle, 0.4D, shot(THShotLib.FORM_CRYSTAL, THShotLib.YELLOW, SIZE[FORM_CRYSTAL], 5.0F), 6, 0.6D, 45.0F - MathHelper.sin(time / 20F) * 40F, (float)(time * -2F));
    	}
    	if(time < 150 && time % 20 == 19)
    	{
    		Vec3 angle = user.getLookVec();
    		if(target.getHealth() > 0F)
    		{
    			angle = angle_UserToTarget();
    		}
    		//angle = THShotLib.getVecFromAngle(card.rotationYaw,card.rotationPitch);
    		THShotLib.createRingShot(user, pos_User(), angle, 0.4D, shot(THShotLib.FORM_CRYSTAL, THShotLib.RED, SIZE[FORM_CRYSTAL], 5.0F), 32, 0.6D, 45.0F, (float)(time * -2F));
    	}
	}
}
