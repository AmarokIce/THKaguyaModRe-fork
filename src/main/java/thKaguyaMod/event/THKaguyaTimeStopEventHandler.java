package thKaguyaMod.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraftforge.event.entity.EntityEvent;

/** Entityが時を止められているときの処理 */
public class THKaguyaTimeStopEventHandler
{
	
	@SubscribeEvent
	public void CanUpdate(EntityEvent event) {
		Entity entity = event.entity;
		if(entity.worldObj.isRemote) {
			//return;
		}
		//entity.posY += 0.2D;
		//entity.motionY = 0.5D;
		//event.
	}
}