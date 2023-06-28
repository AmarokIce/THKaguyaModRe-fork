package thKaguyaMod.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderCreeper;
import thKaguyaMod.CommonProxy;
import thKaguyaMod.client.render.*;
import thKaguyaMod.client.render.effect.RenderHakurouReflecter;
import thKaguyaMod.client.render.effect.RenderMiracleCircle;
import thKaguyaMod.client.render.effect.RenderSpellCardCircle;
import thKaguyaMod.client.render.living.*;
import thKaguyaMod.client.render.shot.*;
import thKaguyaMod.entity.EntityDivineSpirit;
import thKaguyaMod.entity.EntityShotMaterial;
import thKaguyaMod.entity.EntityTHItem;
import thKaguyaMod.entity.effect.*;
import thKaguyaMod.entity.item.*;
import thKaguyaMod.entity.living.*;
import thKaguyaMod.entity.shot.*;
import thKaguyaMod.entity.spellcard.EntitySpellCard;
import thKaguyaMod.tileentity.TileEntityDivineSpirit;

//import thkaguyamod.*;

// クライアント側のみのクラスはこのアノテーションをつける
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	public static int hinezumiID;
	
	public void registerTextures()
	{
		//MinecraftForgeClient.preloadTexture("/gui/thKaguyaTerrain.png");
		//MinecraftForgeClient.preloadTexture("/armor/hinezumi_1.png");
	}
	
	public void registerRenderers()
	{	
		// Entityのクラスと描画, モデルを結びつける
		RenderingRegistry.registerEntityRenderingHandler( EntityTHFairy.class						, new RenderTHFairy()					);
		RenderingRegistry.registerEntityRenderingHandler( EntitySunFlowerFairy.class				, new RenderSunFlowerFairy()			);
		RenderingRegistry.registerEntityRenderingHandler( EntityTHPhantom.class					, new RenderTHPhantom()					);
		RenderingRegistry.registerEntityRenderingHandler( EntityFamiliar.class						, new RenderFamiliar()					);
		RenderingRegistry.registerEntityRenderingHandler( EntityCirno.class						, new RenderCirno()						);
		RenderingRegistry.registerEntityRenderingHandler( EntityRumia.class						, new RenderRumia()						);
		RenderingRegistry.registerEntityRenderingHandler( EntityToziko.class						, new RenderToziko()					);
		RenderingRegistry.registerEntityRenderingHandler( EntitySanae.class						, new RenderSanae()						);
		RenderingRegistry.registerEntityRenderingHandler( EntityRinnosuke.class					, new RenderRinnosuke()					);
		RenderingRegistry.registerEntityRenderingHandler( EntityWriggle.class						, new RenderWriggle()					);
		RenderingRegistry.registerEntityRenderingHandler( EntityCursedDecoyDoll.class				, new RenderCursedDecoyDoll()			);
		RenderingRegistry.registerEntityRenderingHandler( EntitySakuya.class						, new RenderSakuya()						);
		
		RenderingRegistry.registerEntityRenderingHandler( EntityReimu.class						, new RenderReimu()						);
		RenderingRegistry.registerEntityRenderingHandler( EntityMiko.class							, new RenderMiko()						);
		
		RenderingRegistry.registerEntityRenderingHandler( EntityShotMaterial.class					, new RenderShotMaterial()				);
		RenderingRegistry.registerEntityRenderingHandler(EntityTHItem.class						, new RenderTHItem()					);
		RenderingRegistry.registerEntityRenderingHandler( EntityDragonNeckJewel.class				, new RenderRyuuLightningBolt()			);
		//RenderingRegistry.registerEntityRenderingHandler( EntityColorLightningBolt.class			, new RenderColorLightningBolt()		);
		RenderingRegistry.registerEntityRenderingHandler( EntityKinkakuzi.class					, new RenderKinkakuzi()					);
		RenderingRegistry.registerEntityRenderingHandler( EntityPendulum.class						, new RenderPendulum()					);
		RenderingRegistry.registerEntityRenderingHandler( EntitySakuyaWatch.class					, new RenderPrivateSquare()				);
		RenderingRegistry.registerEntityRenderingHandler( EntitySakuyaStopWatch.class				, new RenderSakuyaStopWatch()			);
		RenderingRegistry.registerEntityRenderingHandler( EntitySilverKnife.class					, new RenderSilverKnife()				);
		RenderingRegistry.registerEntityRenderingHandler( EntitySukima.class						, new RenderSukima()					);
		RenderingRegistry.registerEntityRenderingHandler( EntityMazinkyoukan.class					, new RenderMazinkyoukan()				);
		RenderingRegistry.registerEntityRenderingHandler( EntityHisou.class						, new RenderHisou()						);
		RenderingRegistry.registerEntityRenderingHandler( EntityMiniHakkero.class					, new RenderMiniHakkero()				);
		RenderingRegistry.registerEntityRenderingHandler( EntityMiracleCircle.class				, new RenderMiracleCircle()				);
		RenderingRegistry.registerEntityRenderingHandler( EntitySanaeWind.class					, new RenderSanaeWind()					);	
		RenderingRegistry.registerEntityRenderingHandler( EntityCirnoIceBox.class					, new RenderCirnoIceBox()				);
		//RenderingRegistry.registerEntityRenderingHandler( EntityMasterSpark.class					, new RenderMasterSpark()				);
		RenderingRegistry.registerEntityRenderingHandler( EntityHomingAmulet.class					, new RenderHomingAmulet()				);
		RenderingRegistry.registerEntityRenderingHandler( EntityYuukaParasol.class					, new RenderYuukaParasol()				);
		RenderingRegistry.registerEntityRenderingHandler( EntityAjaRedStoneEffect.class			, new RenderAjaRedStoneEffect()			);
		RenderingRegistry.registerEntityRenderingHandler( EntityTHShot.class						, new RenderTHShot()					);
		RenderingRegistry.registerEntityRenderingHandler( EntityTHLaser.class						, new RenderTHLaser()					);
		RenderingRegistry.registerEntityRenderingHandler( EntitySpellCard.class					, new RenderSpellCard()					);
		RenderingRegistry.registerEntityRenderingHandler( EntityMusouFuuin.class					, new RenderMusouFuuin()				);
		//RenderingRegistry.registerEntityRenderingHandler( EntityHakureiShield.class				, new RenderHakureiShield()				);
		//RenderingRegistry.registerEntityRenderingHandler( EntityTHHenyoriLaser.class				, new RenderTHHenyoriLaser()			);
		RenderingRegistry.registerEntityRenderingHandler( EntityDanmakuCreeper.class				, new RenderCreeper()					);
		
		RenderingRegistry.registerEntityRenderingHandler( EntityMarisaBroom.class					, new RenderMarisaBroom()				);
		RenderingRegistry.registerEntityRenderingHandler( EntityNuclearShot.class					, new RenderNuclearShot()				);
		RenderingRegistry.registerEntityRenderingHandler( EntityHakurouReflecter.class				, new RenderHakurouReflecter()			);
		RenderingRegistry.registerEntityRenderingHandler( EntityOnmyoudama.class					, new RenderOnmyoudama()				);
		RenderingRegistry.registerEntityRenderingHandler( EntityTHSetLaser.class					, new RenderTHLaser()					);
		RenderingRegistry.registerEntityRenderingHandler( EntityDivineSpirit.class					, new RenderDivineSpirit()				);
		RenderingRegistry.registerEntityRenderingHandler( EntitySpellCardCircle.class				, new RenderSpellCardCircle()			);
		RenderingRegistry.registerEntityRenderingHandler( EntitySpiritualStrikeTalisman.class		, new RenderSpiritualStrikeTalisman()	);
		//RenderingRegistry.registerEntityRenderingHandler( EntitySpiritualStrikeTalisman.class		, new RenderSpiritualStrikeTalisman()	);
		
		//RenderingRegistry.addNewArmourRendererPrefix("hinezumiw");
		
		RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new RenderDivineSpiritBlock());
		//RenderingRegistry.registerBlockHandler(new RenderDivineSpiritBlock());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDivineSpirit.class, new TileEntityDivineSpiritRenderer());
	}
	
	public int addArmor(String armor)
	{
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}
	
	@Override
	public int getNewRenderType()
	{
		return RenderingRegistry.getNextAvailableRenderId();
	}

}