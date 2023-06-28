package thKaguyaMod;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockDispenser;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import thKaguyaMod.entity.EntityDivineSpirit;
import thKaguyaMod.entity.EntityShotMaterial;
import thKaguyaMod.entity.EntityTHItem;
import thKaguyaMod.entity.effect.*;
import thKaguyaMod.entity.item.*;
import thKaguyaMod.entity.living.*;
import thKaguyaMod.entity.shot.*;
import thKaguyaMod.entity.spellcard.*;
import thKaguyaMod.init.THKaguyaBlocks;
import thKaguyaMod.init.THKaguyaConfig;
import thKaguyaMod.init.THKaguyaItems;
import thKaguyaMod.item.ItemBlockDanmakuCraftingTable;
import thKaguyaMod.item.ItemBlockDivineSpirit;
import thKaguyaMod.item.ItemTHShot;
import thKaguyaMod.registry.DanmakuPatternRegistry;
import thKaguyaMod.registry.SpellCardRegistry;

import java.lang.reflect.Constructor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonProxy
{
    static int nextID= 20+EntityRegistry.findGlobalUniqueEntityId();
    public void registerTextures()
    {
    }

    public void registerRenderers()
    {
        /*
         サーバー側では何もしない
         クライアント側でのみ必要な処理はこのように空のメソッドを用意し,
         CommonProxyを継承したClientProxyで行う
        */
    }
    
    /** アイテムを登録する */
    public void registerItems()
    {
        //アイテムの内部名を決める
        GameRegistry.registerItem( THKaguyaItems.hourai_Jeweled_branch        ,stringHumpToUnderline("JeweledBranchofHourai"         ));
        GameRegistry.registerItem( THKaguyaItems.dragon_neck_jewel            ,stringHumpToUnderline("JewelfromtheDragonsNeck"       ));
        GameRegistry.registerItem( THKaguyaItems.buddha_stone_bowl            ,stringHumpToUnderline("BuddhasStoneBowl"              ));
        GameRegistry.registerItem( THKaguyaItems.fire_rat_robe                ,stringHumpToUnderline("RobeoftheFireRat"              ));
        GameRegistry.registerItem( THKaguyaItems.swallow_cowrie_shell         ,stringHumpToUnderline("SwallowsCowrieShell"           ));
        GameRegistry.registerItem( THKaguyaItems.hourai_pearl                 ,stringHumpToUnderline("ColorPearl"                    ));
        GameRegistry.registerItem( THKaguyaItems.kinkakuji                    ,stringHumpToUnderline("SeamlessCeilingofKinkakuji"    ));
        GameRegistry.registerItem( THKaguyaItems.aja_red_stone                ,stringHumpToUnderline("RedStoneofAja"                 ));
        GameRegistry.registerItem( THKaguyaItems.hakurei_miko_stick           ,stringHumpToUnderline("HakureiShrineMaidenStick"      ));
        GameRegistry.registerItem( THKaguyaItems.amulet                       ,stringHumpToUnderline("HomingAmulet"                  ));
        GameRegistry.registerItem( THKaguyaItems.yin_yang_orb                 ,stringHumpToUnderline("YinYangOrb"                    ));
        GameRegistry.registerItem( THKaguyaItems.bloodthirsty_yin_yang_orb    ,stringHumpToUnderline("BloodthirstyYinYangOrb"        ));
        GameRegistry.registerItem( THKaguyaItems.magic_bloom                  ,stringHumpToUnderline("MagicBroom"                    ));
        GameRegistry.registerItem( THKaguyaItems.mini_hakkero                 ,stringHumpToUnderline("MiniHakkero"                   ));
        GameRegistry.registerItem( THKaguyaItems.marisa_hat                   ,stringHumpToUnderline("MarisasHat"                    ));
        GameRegistry.registerItem( THKaguyaItems.icicle_sword                 ,stringHumpToUnderline("IcicleSword"                   ));
        GameRegistry.registerItem( THKaguyaItems.silver_knife                 ,stringHumpToUnderline("SilverKnife"                   ));
        GameRegistry.registerItem( THKaguyaItems.sakuya_stopwatch             ,stringHumpToUnderline("SakuyasStopwatch"              ));
        GameRegistry.registerItem( THKaguyaItems.sakuya_watch                 ,stringHumpToUnderline("SakuyasPocketWatch"            ));
        GameRegistry.registerItem( THKaguyaItems.gungnir                      ,stringHumpToUnderline("SpeartheGungnir"               ));
        GameRegistry.registerItem( THKaguyaItems.laevateinn                   ,stringHumpToUnderline("Laevateinn"                    ));
        GameRegistry.registerItem( THKaguyaItems.cursedDecoyDoll              ,stringHumpToUnderline("CursedDecoyDoll"               ));
        GameRegistry.registerItem( THKaguyaItems.hakurouken                   ,stringHumpToUnderline("Hakurouken"                    ));
        GameRegistry.registerItem( THKaguyaItems.roukanken                    ,stringHumpToUnderline("Roukanken"                     ));
        GameRegistry.registerItem( THKaguyaItems.yuyuko_fan                   ,stringHumpToUnderline("YuyukosFan"                    ));
        GameRegistry.registerItem( THKaguyaItems.soulTorch                    ,stringHumpToUnderline("SoulTorch"                     ));
        GameRegistry.registerItem( THKaguyaItems.gap                          ,stringHumpToUnderline("Gap"                           ));
        GameRegistry.registerItem( THKaguyaItems.gapFoldingUmbrella           ,stringHumpToUnderline("GapFoldingUmbrella"            ));
        GameRegistry.registerItem( THKaguyaItems.ibuki_gourd                  ,stringHumpToUnderline("IbukiGourd"                    ));
        GameRegistry.registerItem( THKaguyaItems.yuuka_parasol                ,stringHumpToUnderline("YuukasParasol"                 ));
        GameRegistry.registerItem( THKaguyaItems.death_scythe                 ,stringHumpToUnderline("ScytheofDeath"                 ));
        GameRegistry.registerItem( THKaguyaItems.remorse_rod                  ,stringHumpToUnderline("RodofRemorse"                  ));
        GameRegistry.registerItem( THKaguyaItems.tengu_fan                    ,stringHumpToUnderline("TengusFan"                     ));
        GameRegistry.registerItem( THKaguyaItems.kappa_water_pistol           ,stringHumpToUnderline("KappasWaterPistol"             ));
        GameRegistry.registerItem( THKaguyaItems.kappa_cap                    ,stringHumpToUnderline("KappaCap"                      ));
        GameRegistry.registerItem( THKaguyaItems.wind_miko_stick              ,stringHumpToUnderline("WindShrineMaidenStick"         ));
        GameRegistry.registerItem( THKaguyaItems.illness_recovery_charm       ,stringHumpToUnderline("IllnessRecoveryCharm"          ));
        GameRegistry.registerItem( THKaguyaItems.onbashira                    ,stringHumpToUnderline("Onbashira"                     ));
        GameRegistry.registerItem( THKaguyaItems.suwako_hat                   ,stringHumpToUnderline("SuwakosHat"                    ));
        GameRegistry.registerItem( THKaguyaItems.heavenly_peach               ,stringHumpToUnderline("HeavenlyPeach"                 ));
        GameRegistry.registerItem( THKaguyaItems.hisou_sword                  ,stringHumpToUnderline("SwordofScarletPerception"      ));
        GameRegistry.registerItem( THKaguyaItems.third_eye                    ,stringHumpToUnderline("3rdEye"                        ));
        GameRegistry.registerItem( THKaguyaItems.nuclear_control_rod          ,stringHumpToUnderline("NuclearControlRod"             ));
        GameRegistry.registerItem( THKaguyaItems.closed_third_eye             ,stringHumpToUnderline("Closed3rdEye"                  ));
        GameRegistry.registerItem( THKaguyaItems.nazrin_pendulum              ,stringHumpToUnderline("NazrinPendulum"                ));
        GameRegistry.registerItem( THKaguyaItems.ship_ghost_dipper_empty      ,stringHumpToUnderline("ShipGhostsDipperempty"         ));
        GameRegistry.registerItem( THKaguyaItems.ship_ghost_dipper_fill       ,stringHumpToUnderline("ShipGhostsDipperfill"          ));
        GameRegistry.registerItem( THKaguyaItems.sorcerer_sutra_scroll        ,stringHumpToUnderline("SorcerersSutraScroll"          ));
        GameRegistry.registerItem( THKaguyaItems.wall_passing_chisel          ,stringHumpToUnderline("Wall-PassingChisel"            ));
        GameRegistry.registerItem( THKaguyaItems.toyosatomimi_sword           ,stringHumpToUnderline("SacredSwordofToyosatomimi"     ));
        GameRegistry.registerItem( THKaguyaItems.miracle_mallet               ,stringHumpToUnderline("MiracleMallet"                 ));
        GameRegistry.registerItem( THKaguyaItems.spiritual_strike_talisman    ,stringHumpToUnderline("SpiritualStrikeTalisman"       ));
        GameRegistry.registerItem( THKaguyaItems.shot_material                ,stringHumpToUnderline("ShotMaterial"                  ));
        GameRegistry.registerItem( THKaguyaItems.power_item                        ,stringHumpToUnderline("PowerUpItem"                   ));
        GameRegistry.registerItem( THKaguyaItems.point_item                        ,stringHumpToUnderline("PointItem"                     ));
        GameRegistry.registerItem( THKaguyaItems.bomb_item                         ,stringHumpToUnderline("SpellCardItem"                 ));
        GameRegistry.registerItem( THKaguyaItems.extend_item                       ,stringHumpToUnderline("ExtendItem"                    ));
        GameRegistry.registerItem( THKaguyaItems.shot_item                         ,stringHumpToUnderline("Shot"                          ));
        GameRegistry.registerItem( THKaguyaItems.laser_item                        ,stringHumpToUnderline("Laser"                         ));
        GameRegistry.registerItem( THKaguyaItems.spell_card                   ,stringHumpToUnderline("SpellCard"                     ));

		//GameRegistry.registerItem( THKaguyaItems.frandreRodItem            		, "FrandreRod"                    );
		//GameRegistry.registerItem( THKaguyaItems.roukanSenpuuzin            		, "RoukanSenpuuzin"                );
		//GameRegistry.registerItem( THKaguyaItems.houtouItem                		, "BishamontenJeweledPagoda"    );
		//GameRegistry.registerItem( THKaguyaItems.danmakuMemoItem            		, "DanmakuMemo"                    );
    }
    
    /** ブロックを登録する */
    public void registerBlocks()
    {
        GameRegistry.registerBlock(THKaguyaBlocks.danmaku_crafting_table, ItemBlockDanmakuCraftingTable.class, "DanmakuCraftingTable");
        GameRegistry.registerBlock(THKaguyaBlocks.divine_spirit, ItemBlockDivineSpirit.class, "DivineSpirit");
    }
    
    /** エンティティを登録する */
    public void registerEntitys()
    {

        /*
         サーバーとクライアントのエンティティを同期させるメソッド
         各引数はそれぞれ以下のとおり
         Entityのクラス,
         Entityの内部名,
         このmod内で使用する同期取り用のID,
         @Modのクラス(ここに書くのであればthis, そうでないならinstanceを参照)
         更新可能な距離
         更新頻度(tickごと)
         Entityが速度情報を持つかどうか
        */

        /*
         * entityId will generator auto. Why we should int? No! Non-standard case, registration name with spaces! The entire workspace is a disaster!
         * A DISASTER! DISASTER! DISASTER!
         */
        int entityId = 0;
        World world = Minecraft.getMinecraft().theWorld;

        initEntity(EntityShotMaterial.class              ,stringHumpToUnderline("ShotMaterial")              ,entityId++           , THKaguyaCore.instance, 40, 1, false   );
        initEntity(EntityDragonNeckJewel.class           ,stringHumpToUnderline("BrilliantDragonBullet")     ,entityId++           , THKaguyaCore.instance, 80, 1, true    );
        initEntity(EntityTHItem.class                    ,stringHumpToUnderline("THItem")                    ,entityId++           , THKaguyaCore.instance, 40, 1, true    );
        initEntity(EntityKinkakuzi.class                 ,stringHumpToUnderline("Kinkakuzi")                 ,entityId++           , THKaguyaCore.instance,250, 1, true    );
        initEntity(EntitySilverKnife.class               ,stringHumpToUnderline("SilverKnife")               ,entityId++           , THKaguyaCore.instance, 40, 1, true    );
        initEntity(EntitySakuyaWatch.class               ,stringHumpToUnderline("PrivateSquare")             ,entityId++           , THKaguyaCore.instance, 40, 1, true    );
        initEntity(EntitySukima.class                    ,stringHumpToUnderline("Sukima")                    ,entityId++           , THKaguyaCore.instance, 40, 5, false   );
        initEntity(EntityMazinkyoukan.class              ,stringHumpToUnderline("Mazinkyoukan")              ,entityId++           , THKaguyaCore.instance, 40, 1, true    );
        initEntity(EntityHisou.class                     ,stringHumpToUnderline("Hisou")                     ,entityId++           , THKaguyaCore.instance, 40, 1, true    );
        initEntity(EntityPendulum.class                  ,stringHumpToUnderline("NazrinPendulum")            ,entityId++           , THKaguyaCore.instance, 40, 1, true    );
        initEntity(EntityMiracleCircle.class             ,stringHumpToUnderline("MiracleCircle")             ,entityId++           , THKaguyaCore.instance, 40, 1, false   );
        initEntity(EntitySanaeWind.class                 ,stringHumpToUnderline("SanaeWind")                 ,entityId++           , THKaguyaCore.instance, 80, 1, true    );
        initEntity(EntityCirnoIceBox.class               ,stringHumpToUnderline("CirnoIceBox")               ,entityId++           , THKaguyaCore.instance, 80,10, false   );
        initEntity(EntitySpiritualStrikeTalisman.class   ,stringHumpToUnderline("SpiritualStrikeTalisman")   ,entityId++           , THKaguyaCore.instance, 80,10, false   );
        initEntity(EntityMiniHakkero.class               ,stringHumpToUnderline("MiniHakkero")               ,entityId++           , THKaguyaCore.instance, 40, 5, false   );
        initEntity(EntityHomingAmulet.class              ,stringHumpToUnderline("HomingAmulet")              ,entityId++           , THKaguyaCore.instance, 80, 1, true    );
        initEntity(EntityTHSetLaser.class                ,stringHumpToUnderline("THSetLaser")                ,entityId++           , THKaguyaCore.instance, 40, 1, true    );
        initEntity(EntityYuukaParasol.class              ,stringHumpToUnderline("YuukaParasol")              ,entityId++           , THKaguyaCore.instance, 40, 1, true    );
        initEntity(EntityAjaRedStoneEffect.class         ,stringHumpToUnderline("AjaRedStoneEffect")         ,entityId++           , THKaguyaCore.instance, 40, 1, true    );
        initEntity(EntityMarisaBroom.class               ,stringHumpToUnderline("MarisaBroom")               ,entityId++           , THKaguyaCore.instance, 80, 1, true    );
        initEntity(EntityTHShot.class                    ,stringHumpToUnderline("NormalShot")                ,entityId++           , THKaguyaCore.instance, 40, 1, false   );
        initEntity(EntityTHLaser.class                   ,stringHumpToUnderline("NormalLaser")               ,entityId++           , THKaguyaCore.instance, 40, 1, false   );
        initEntity(EntitySpellCard.class                 ,stringHumpToUnderline("SpellCard")                 ,entityId++           , THKaguyaCore.instance, 40, 5, false   );
        initEntity(EntityMusouFuuin.class                ,stringHumpToUnderline("MusouFuuin")                ,entityId++           , THKaguyaCore.instance, 80, 1, true    );
        initEntity(EntitySakuyaStopWatch.class           ,stringHumpToUnderline("SakuyaStopWatch")           ,entityId++           , THKaguyaCore.instance, 40, 1, true    );
        initEntity(EntityNuclearShot.class               ,stringHumpToUnderline("NuclearShot")               ,entityId++           , THKaguyaCore.instance, 80, 1, true    );
        initEntity(EntityHakurouReflecter.class          ,stringHumpToUnderline("HakurouReflecter")          ,entityId++           , THKaguyaCore.instance, 40, 1, true    );
        initEntity(EntityOnmyoudama.class                ,stringHumpToUnderline("Onmyoudama")                ,entityId++           , THKaguyaCore.instance, 80, 1, true    );
        initEntity(EntityDivineSpirit.class              ,stringHumpToUnderline("DivineSpirit")              ,entityId++           , THKaguyaCore.instance, 80, 1, true    );
        initEntity(EntitySpellCardCircle.class           ,stringHumpToUnderline("SpellCardCircle")           ,entityId++           , THKaguyaCore.instance, 80, 1, true    );
        initEntity(EntityCursedDecoyDoll.class           ,stringHumpToUnderline("CursedDecoyDoll")           ,entityId++           , THKaguyaCore.instance, 60, 5, false   );
        initEntity(EntityTHFairy.class                   ,stringHumpToUnderline("THFairy")                   ,entityId++           , THKaguyaCore.instance, 80, 1, true    );
        initEntity(EntityCirno.class                     ,stringHumpToUnderline("Cirno")                     ,entityId++           , THKaguyaCore.instance, 80, 1, true    );
        initEntity(EntityRumia.class                     ,stringHumpToUnderline("Rumia")                     ,entityId++           , THKaguyaCore.instance, 80, 1, true    );
        initEntity(EntityToziko.class                    ,stringHumpToUnderline("Toziko")                    ,entityId++           , THKaguyaCore.instance, 80, 1, true    );
        initEntity(EntityTHPhantom.class                 ,stringHumpToUnderline("THPhantom")                 ,entityId++           , THKaguyaCore.instance, 80, 1, true    );
        initEntity(EntitySanae.class                     ,stringHumpToUnderline("Sanae")                     ,entityId++           , THKaguyaCore.instance, 80, 1, true    );
        initEntity(EntityReimu.class                     ,stringHumpToUnderline("Reimu")                     ,entityId++           , THKaguyaCore.instance, 80, 1, true    );
        initEntity(EntityMiko.class                      ,stringHumpToUnderline("Miko")                      ,entityId++           , THKaguyaCore.instance, 80, 1, true    );
        initEntity(EntityWriggle.class                   ,stringHumpToUnderline("Wriggle")                   ,entityId++           , THKaguyaCore.instance, 80, 1, true    );
        initEntity(EntitySakuya.class                    ,stringHumpToUnderline("Sakuya")                    ,entityId++           , THKaguyaCore.instance, 80, 1, true    );
        initEntity(EntityRinnosuke.class                 ,stringHumpToUnderline("Rinnosuke")                 ,entityId++           , THKaguyaCore.instance, 80, 1, true    );
        initEntity(EntitySunFlowerFairy.class            ,stringHumpToUnderline("SunFlowerFairy")            ,entityId++           , THKaguyaCore.instance, 80, 1, true    );
        initEntity(EntityFamiliar.class                  ,stringHumpToUnderline("Familiar")                  ,entityId++           , THKaguyaCore.instance, 80, 1, true    );
        initEntity(EntityDanmakuCreeper.class            ,stringHumpToUnderline("Hanabeeper")                ,entityId             , THKaguyaCore.instance, 80, 1, true    );
        EntityRegistry.registerGlobalEntityID( EntityTHFairy.class                ,stringHumpToUnderline("THFairy")                    ,nextID++            , 0xFFFF80, 0xC0C000      );
        EntityRegistry.registerGlobalEntityID( EntitySunFlowerFairy.class         ,stringHumpToUnderline("SunflowerFairy")             ,nextID++            , 0xDDDD60, 0xA0A000      );
        EntityRegistry.registerGlobalEntityID( EntityTHPhantom.class              ,stringHumpToUnderline("THPhantom")                  ,nextID++            , 0xFFFFF0, 0xFFFFFF      );
        EntityRegistry.registerGlobalEntityID( EntityFamiliar.class               ,stringHumpToUnderline("Familiar")                   ,nextID++                                                                    );
        EntityRegistry.registerGlobalEntityID( EntityCirno.class                  ,stringHumpToUnderline("Cirno")                      ,nextID++            , 0x00FFE0, 0x00E0E0      );
        EntityRegistry.registerGlobalEntityID( EntityRumia.class                  ,stringHumpToUnderline("Rumia")                      ,nextID++            , 0x671503, 0xC0C010      );
        EntityRegistry.registerGlobalEntityID( EntityToziko.class                 ,stringHumpToUnderline("Toziko")                     ,nextID++            , 0x60D683, 0x376E91      );
        EntityRegistry.registerGlobalEntityID( EntitySanae.class                  ,stringHumpToUnderline("Sanae")                      ,nextID++            , 0x6BD245, 0x2937E0      );
        EntityRegistry.registerGlobalEntityID( EntityDanmakuCreeper.class         ,stringHumpToUnderline("Hanabeeper")                 ,nextID++            , 0x0FF080, 0xC0C000      );
        EntityRegistry.registerGlobalEntityID( EntityRinnosuke.class              ,stringHumpToUnderline("Rinnosuke")                  ,nextID++            , 0x373737, 0x2D2DE0      );
        EntityRegistry.registerGlobalEntityID( EntityWriggle.class                ,stringHumpToUnderline("Wriggle")                    ,nextID++            , 0x15224F, 0x83C826      );
        EntityRegistry.registerGlobalEntityID( EntitySakuya.class                 ,stringHumpToUnderline("Sakuya")                     ,nextID++            , 0xC1C1D6, 0x3053E7      );
        EntityRegistry.registerGlobalEntityID( EntityReimu.class                  ,stringHumpToUnderline("Reimu")                      ,THKaguyaConfig.entityIdReimu                                                );
        EntityRegistry.registerGlobalEntityID( EntityMiko.class                   ,stringHumpToUnderline("Miko")                       ,THKaguyaConfig.entityIdReimu + 1                                            );

    }

    /**
     * Make the Name in lowerCase. From Someoneice's StringHelper.
     * @author Someoneice
     * @param str The String input.
     * @return The String with lowerCase with _ .
     */
    public String stringHumpToUnderline(String str) {
        String letter = "([A-Z])";
        Matcher matcher = Pattern.compile(letter).matcher(str);
        while (matcher.find()) {
            String getStr = matcher.group();
            str = str.replaceFirst(getStr, getStr.toLowerCase());
            str = str.replaceAll(getStr, "_" + getStr.toLowerCase());
        }
        return str;
    }

    /**
     * Registry entity and make spawn egg auto.
     * @author Someoneice
     */
    public void initEntity(Class<? extends Entity> entity, String entityName, int id, Object mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
        EntityRegistry.registerModEntity(entity, entityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates);
        new ItemSpawnEgg(entityName, entity);
    }

    class ItemSpawnEgg extends Item {
        Class<? extends Entity> clazz;
        public ItemSpawnEgg(String name, Class<? extends Entity> entity) {
            this.clazz = entity;
            String rname = name + "_egg";
            this.setUnlocalizedName(rname);
            this.setTextureName("THKaguyaMod:" + rname);

            this.setCreativeTab(CreativeTabs.tabMisc);
            GameRegistry.registerItem(this, rname);
        }

        @Override
        public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float hx, float hy, float hz) {
            Entity entity = null;
            if (clazz == EntityShotMaterial.class              ) entity = new EntityShotMaterial(world);
            if (clazz == EntityDragonNeckJewel.class           ) entity = new EntityDragonNeckJewel(world);
            if (clazz == EntityTHItem.class                    ) entity = new EntityTHItem(world);
            if (clazz == EntityKinkakuzi.class                 ) entity = new EntityKinkakuzi(world);
            if (clazz == EntitySilverKnife.class               ) entity = new EntitySilverKnife(world);
            if (clazz == EntitySakuyaWatch.class               ) entity = new EntitySakuyaWatch(world);
            if (clazz == EntitySukima.class                    ) entity = new EntitySukima(world);
            if (clazz == EntityMazinkyoukan.class              ) entity = new EntityMazinkyoukan(world);
            if (clazz == EntityHisou.class                     ) entity = new EntityHisou(world);
            if (clazz == EntityPendulum.class                  ) entity = new EntityPendulum(world);
            if (clazz == EntityMiracleCircle.class             ) entity = new EntityMiracleCircle(world);
            if (clazz == EntitySanaeWind.class                 ) entity = new EntitySanaeWind(world);
            if (clazz == EntityCirnoIceBox.class               ) entity = new EntityCirnoIceBox(world);
            if (clazz == EntitySpiritualStrikeTalisman.class   ) entity = new EntitySpiritualStrikeTalisman(world);
            if (clazz == EntityMiniHakkero.class               ) entity = new EntityMiniHakkero(world);
            if (clazz == EntityHomingAmulet.class              ) entity = new EntityHomingAmulet(world);
            if (clazz == EntityTHSetLaser.class                ) entity = new EntityTHSetLaser(world);
            if (clazz == EntityYuukaParasol.class              ) entity = new EntityYuukaParasol(world);
            if (clazz == EntityAjaRedStoneEffect.class         ) entity = new EntityAjaRedStoneEffect(world);
            if (clazz == EntityMarisaBroom.class               ) entity = new EntityMarisaBroom(world);
            if (clazz == EntityTHShot.class                    ) entity = new EntityTHShot(world);
            if (clazz == EntityTHLaser.class                   ) entity = new EntityTHLaser(world);
            if (clazz == EntitySpellCard.class                 ) entity = new EntitySpellCard(world);
            if (clazz == EntityMusouFuuin.class                ) entity = new EntityMusouFuuin(world);
            if (clazz == EntitySakuyaStopWatch.class           ) entity = new EntitySakuyaStopWatch(world);
            if (clazz == EntityNuclearShot.class               ) entity = new EntityNuclearShot(world);
            if (clazz == EntityHakurouReflecter.class          ) entity = new EntityHakurouReflecter(world);
            if (clazz == EntityOnmyoudama.class                ) entity = new EntityOnmyoudama(world);
            if (clazz == EntityDivineSpirit.class              ) entity = new EntityDivineSpirit(world);
            if (clazz == EntitySpellCardCircle.class           ) entity = new EntitySpellCardCircle(world);
            if (clazz == EntityCursedDecoyDoll.class           ) entity = new EntityCursedDecoyDoll(world);
            if (clazz == EntityTHFairy.class                   ) entity = new EntityTHFairy(world);
            if (clazz == EntityCirno.class                     ) entity = new EntityCirno(world);
            if (clazz == EntityRumia.class                     ) entity = new EntityRumia(world);
            if (clazz == EntityToziko.class                    ) entity = new EntityToziko(world);
            if (clazz == EntityTHPhantom.class                 ) entity = new EntityTHPhantom(world);
            if (clazz == EntitySanae.class                     ) entity = new EntitySanae(world);
            if (clazz == EntityReimu.class                     ) entity = new EntityReimu(world);
            if (clazz == EntityMiko.class                      ) entity = new EntityMiko(world);
            if (clazz == EntityWriggle.class                   ) entity = new EntityWriggle(world);
            if (clazz == EntitySakuya.class                    ) entity = new EntitySakuya(world);
            if (clazz == EntityRinnosuke.class                 ) entity = new EntityRinnosuke(world);
            if (clazz == EntitySunFlowerFairy.class            ) entity = new EntitySunFlowerFairy(world);
            if (clazz == EntityFamiliar.class                  ) entity = new EntityFamiliar(world);
            if (clazz == EntityDanmakuCreeper.class            ) entity = new EntityDanmakuCreeper(world);

            if (entity == null) return false;

            entity.posX = x;
            entity.posY = y + 1.0D;
            entity.posZ = z;

            ((EntityLiving) entity).setHealth(((EntityLiving) entity).getMaxHealth());

            return world.spawnEntityInWorld(entity);
        }
    }
    
    /**
     * エンティティのスポーン設定登録
     */
    public void registerEntitySpawn()
    {        
        /*MOBをスポーンさせる
        entityClass    : スポーンさせるEntityのclass
        weightedProb   : スポーンする割合。大きいほどスポーンしやすい
        min            : 一度にスポーンする最低数
        max            : 一度にスポーンする最大数
        typeOfCreature : MOBのタイプ
        biomes         : スポーンするバイオーム
        */
        if(THKaguyaConfig.spawnDanmakuMob)
        {
            if(THKaguyaConfig.spawnFairy)
            {    
                //妖精
                EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.plains);
                EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.birchForest);
                EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.birchForestHills);
                EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.desert);
                EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.extremeHills);
                EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.extremeHillsEdge);
                EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.extremeHillsPlus);
                EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.forest);
                EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.forestHills);
                EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.taiga);
                EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.swampland);
                EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.icePlains);
                EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.iceMountains);
                EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.jungle);
                EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.jungleHills);
                EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.roofedForest);
                EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.swampland);
                EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.taiga);
                EntityRegistry.addSpawn(EntityTHFairy.class,  30, 1, 6,EnumCreatureType.monster, BiomeGenBase.taigaHills);
                
                //EntityRegistry.addSpawn(EntityTHFairy.class,  3, 1, 6,EnumCreatureType.monster, GensoukyouCore.gensoukyouPlains);
                
                //妖精
                EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.plains);
                EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.birchForest);
                EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.birchForestHills);
                EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.desert);
                EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.extremeHills);
                EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.extremeHillsEdge);
                EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.extremeHillsPlus);
                EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.forest);
                EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.forestHills);
                EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.taiga);
                EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.swampland);
                EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.icePlains);
                EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.iceMountains);
                EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.jungle);
                EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.jungleHills);
                EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.roofedForest);
                EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.swampland);
                EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.taiga);
                EntityRegistry.addSpawn(EntitySunFlowerFairy.class,  3, 1, 1,EnumCreatureType.monster, BiomeGenBase.taigaHills);
        

            }
            
            if(THKaguyaConfig.spawnPhantom)
            {
                //幽霊
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.plains);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.beach);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.birchForest);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.birchForestHills);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.coldBeach);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.coldTaiga);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.coldTaigaHills);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.deepOcean);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.desert);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.desertHills);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.extremeHills);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.extremeHillsEdge);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.extremeHillsPlus);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.forest);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.forestHills);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.frozenOcean);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.frozenRiver);
                //EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.hell);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.iceMountains);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.icePlains);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.jungle);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.jungleEdge);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.jungleHills);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.megaTaiga);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.megaTaigaHills);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.mesa);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.mesaPlateau);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.mesaPlateau_F);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.ocean);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.river);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.roofedForest);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.savanna);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.savannaPlateau);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.stoneBeach);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.swampland);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.taiga);
                EntityRegistry.addSpawn(EntityTHPhantom.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.taigaHills);
            }
            
            if(THKaguyaConfig.spawnHanabeeper)
            {
                EntityRegistry.addSpawn(EntityDanmakuCreeper.class,  2, 1, 4,EnumCreatureType.monster, BiomeGenBase.desert);
            }
            
            if(THKaguyaConfig.spawnBoss)
            {
                //チルノ
                EntityRegistry.addSpawn(EntityCirno.class,  2, 1, 1,EnumCreatureType.monster, BiomeGenBase.coldTaiga);
                EntityRegistry.addSpawn(EntityCirno.class,  2, 1, 1,EnumCreatureType.monster, BiomeGenBase.coldTaigaHills);
                EntityRegistry.addSpawn(EntityCirno.class,  2, 1, 1,EnumCreatureType.monster, BiomeGenBase.frozenRiver);
                EntityRegistry.addSpawn(EntityCirno.class,  2, 1, 1,EnumCreatureType.monster, BiomeGenBase.iceMountains);
                EntityRegistry.addSpawn(EntityCirno.class,  2, 1, 1,EnumCreatureType.monster, BiomeGenBase.icePlains);
                
                //ルーミア
                EntityRegistry.addSpawn(EntityRumia.class, 4, 1, 1, EnumCreatureType.monster, BiomeGenBase.forest);
                EntityRegistry.addSpawn(EntityRumia.class, 4, 1, 1, EnumCreatureType.monster, BiomeGenBase.plains);
                EntityRegistry.addSpawn(EntityRumia.class, 7, 1, 1, EnumCreatureType.monster, BiomeGenBase.roofedForest);
                EntityRegistry.addSpawn(EntityRumia.class, 4, 1, 1, EnumCreatureType.monster, BiomeGenBase.taiga);
                EntityRegistry.addSpawn(EntityRumia.class, 4, 1, 1, EnumCreatureType.monster, BiomeGenBase.taigaHills);
                
                //屠自古
                EntityRegistry.addSpawn(EntityToziko.class, 9, 1, 1, EnumCreatureType.monster, BiomeGenBase.forest);
                EntityRegistry.addSpawn(EntityToziko.class, 7, 1, 1, EnumCreatureType.monster, BiomeGenBase.plains);
                EntityRegistry.addSpawn(EntityToziko.class,20, 1, 1, EnumCreatureType.monster, BiomeGenBase.swampland);
                
                //リグル
                EntityRegistry.addSpawn(EntityWriggle.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.birchForest);
                EntityRegistry.addSpawn(EntityWriggle.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.birchForestHills);
                EntityRegistry.addSpawn(EntityWriggle.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.forest);
                EntityRegistry.addSpawn(EntityWriggle.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.forestHills);
                EntityRegistry.addSpawn(EntityWriggle.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.jungle);
                EntityRegistry.addSpawn(EntityWriggle.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.jungleEdge);
                EntityRegistry.addSpawn(EntityWriggle.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.jungleHills);
                EntityRegistry.addSpawn(EntityWriggle.class,  20, 1, 3,EnumCreatureType.monster, BiomeGenBase.river);
            }
            
            //早苗
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.plains);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.beach);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.birchForest);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.birchForestHills);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.coldBeach);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.coldTaiga);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.coldTaigaHills);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.deepOcean);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.desert);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.desertHills);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.extremeHills);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.extremeHillsEdge);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.extremeHillsPlus);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.forest);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.forestHills);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.frozenOcean);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.frozenRiver);
            //EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.hell);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.iceMountains);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.icePlains);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.jungle);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.jungleEdge);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.jungleHills);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.megaTaiga);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.megaTaigaHills);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.mesa);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.mesaPlateau);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.mesaPlateau_F);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.ocean);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.river);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.roofedForest);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.savanna);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.savannaPlateau);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.stoneBeach);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.swampland);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.taiga);
            EntityRegistry.addSpawn(EntitySanae.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.taigaHills);
            
            //咲夜
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.plains);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.beach);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.birchForest);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.birchForestHills);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.coldBeach);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.coldTaiga);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.coldTaigaHills);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.deepOcean);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.desert);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.desertHills);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.extremeHills);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.extremeHillsEdge);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.extremeHillsPlus);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.forest);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.forestHills);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.frozenOcean);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.frozenRiver);
            //EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.hell);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.iceMountains);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.icePlains);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.jungle);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.jungleEdge);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.jungleHills);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.megaTaiga);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.megaTaigaHills);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.mesa);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.mesaPlateau);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.mesaPlateau_F);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.ocean);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.river);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.roofedForest);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.savanna);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.savannaPlateau);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.stoneBeach);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.swampland);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.taiga);
            EntityRegistry.addSpawn(EntitySakuya.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.taigaHills);
            
            //霖之助
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.plains);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.beach);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.birchForest);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.birchForestHills);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.coldBeach);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.coldTaiga);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.coldTaigaHills);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.deepOcean);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.desert);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.desertHills);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.extremeHills);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.extremeHillsEdge);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.extremeHillsPlus);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.forest);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.forestHills);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.frozenOcean);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.frozenRiver);
            //EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.hell);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.iceMountains);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.icePlains);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.jungle);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.jungleEdge);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.jungleHills);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.megaTaiga);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.megaTaigaHills);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.mesa);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.mesaPlateau);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.mesaPlateau_F);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.ocean);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.river);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.roofedForest);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.savanna);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.savannaPlateau);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.stoneBeach);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.swampland);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.taiga);
            EntityRegistry.addSpawn(EntityRinnosuke.class,  1, 0, 1,EnumCreatureType.ambient, BiomeGenBase.taigaHills);
        }
    }
    
    /**
     * スペルカードの登録
     */
    public void registerSpellCards()
    {
        /*　スペルカードの登録は、SpellCardRegistry.registerSpellCard(...)を使う
         * 
         */
        SpellCardRegistry.registerSpellCard( THSC_MusouFuuin.class                    , "thkaguyamod"    , "MusouFuuin"                    ,  0    );
        SpellCardRegistry.registerSpellCard( THSC_MasterSpark.class                    , "thkaguyamod"    , "MasterSpark"                    ,  1    );
        SpellCardRegistry.registerSpellCard( THSC_Kasho_no_Eimin.class                , "thkaguyamod"    , "Kasho_no_Eimin"                ,  2    );
        SpellCardRegistry.registerSpellCard( THSC_MeteonicShower.class                , "thkaguyamod"    , "MeteonicShower"                ,  3    );
        SpellCardRegistry.registerSpellCard( THSC_Nami_to_Tubu_no_Kyoukai.class        , "thkaguyamod"    , "Nami_to_Tubu_no_Kyoukai"        ,  4    );
        SpellCardRegistry.registerSpellCard( THSC_NijuuKokushichou.class                , "thkaguyamod"    , "NijuuKokushichou"            ,  5    );
        SpellCardRegistry.registerSpellCard( THSC_ScarletShoot.class                    , "thkaguyamod"    , "ScarletShoot"                ,  6    );
        SpellCardRegistry.registerSpellCard( THSC_Zenzinrui_no_Hisouten.class        , "thkaguyamod"    , "Zenzinrui_no_Hisouten"        ,  7    );
        SpellCardRegistry.registerSpellCard( THSC_SatuzinDoll.class                    , "thkaguyamod"    , "SatuzinDoll"                    ,  8    );
        SpellCardRegistry.registerSpellCard( THSC_PerfectFreeze.class                , "thkaguyamod"    , "PerfectFreeze"                ,  9    );
        SpellCardRegistry.registerSpellCard( THSC_HikouchuuNest.class                , "thkaguyamod"    , "HikouchuuNest"                , 10    );
        SpellCardRegistry.registerSpellCard( THSC_KappaPororoca.class                , "thkaguyamod"    , "Kappa'sPororoca"                , 11    );
        SpellCardRegistry.registerSpellCard( THSC_StardustReverie.class                , "thkaguyamod"    , "StardustReverie"                , 12    );
        SpellCardRegistry.registerSpellCard( THSC_Kerochan_Fuuu_ni_Makezu.class        , "thkaguyamod"    , "Kerochan_Fuuu_ni_Makezu"        , 13    );
        SpellCardRegistry.registerSpellCard( THSC_MiracleFruit.class                    , "thkaguyamod"    , "MiracleFruit"                , 14    );
        SpellCardRegistry.registerSpellCard( THSC_Fafurotskies_no_Kiseki.class        , "thkaguyamod"    , "Fafrotskies_no_Kiseki"        , 15    );
        SpellCardRegistry.registerSpellCard( THSC_YouryokuSpoiler.class                , "thkaguyamod"    , "YouryokuSpoiler"                , 16    );
        SpellCardRegistry.registerSpellCard( THSC_MosesMiracle.class                    , "thkaguyamod"    , "Moses_no_Kiseki"                , 17    );
        SpellCardRegistry.registerSpellCard( THSC_Yasaka_no_Kamikaze.class            , "thkaguyamod"    , "Yasaka_no_Kamikaze"            , 18    );
        SpellCardRegistry.registerSpellCard( THSC_IcicleFall.class                    , "thkaguyamod"    , "IcicleFall"                    , 19    );
        SpellCardRegistry.registerSpellCard( THSC_StarbowBreak.class                    , "thkaguyamod"    , "StarbowBreak"                , 20    );
        SpellCardRegistry.registerSpellCard( THSC_Catadioptric.class                    , "thkaguyamod"    , "Catadioptric"                , 21    );
        SpellCardRegistry.registerSpellCard( THSC_Mishagujisama.class                , "thkaguyamod"    , "Mishagujisama"                , 22    );
        SpellCardRegistry.registerSpellCard( THSC_RedMagic.class                        , "thkaguyamod"    , "RedMagic"                    , 23    );
        SpellCardRegistry.registerSpellCard( THSC_EternalMeek.class                    , "thkaguyamod"    , "EternalMeek"                    , 24    );
        SpellCardRegistry.registerSpellCard( THSC_NonDirectionalLaser.class            , "thkaguyamod"    , "Non-DirectionalLaser"        , 25    );
        SpellCardRegistry.registerSpellCard( THSC_Kachoufuugetu.class                , "thkaguyamod"    , "KachoufuugetuShoufuurougetu"    , 26    );
        SpellCardRegistry.registerSpellCard( THSC_Spear_the_Gungnir.class            , "thkaguyamod"    , "Spear_the_Gungnir"            , 27    );
        SpellCardRegistry.registerSpellCard( THSC_MoonlightRay.class                    , "thkaguyamod"    , "MoonlightRay"                , 28    );
        SpellCardRegistry.registerSpellCard( THSC_HoukaKenran.class                    , "thkaguyamod"    , "HoukaKenran"                    , 29    );
        SpellCardRegistry.registerSpellCard( THSC_SaikouRanbu.class                    , "thkaguyamod"    , "SaikouRanbu"                    , 30    );
        
        SpellCardRegistry.registerSpellCard( THSC_FujiyamaVolcano.class                , "thkaguyamod"    , "FujiyamaVolcano"                , 33    );
        SpellCardRegistry.registerSpellCard( THSC_LittleBugStorm.class                , "thkaguyamod"    , "LittleBugStorm"                , 34    );
        SpellCardRegistry.registerSpellCard( THSC_MeteorOnEarth.class                , "thkaguyamod"    , "Meteor_on_Earth"                , 35    );
        //SpellCardRegistry.registerSpellCard( THSC_BrilliantDragonBullet.class        , "thkaguyamod"    , "BrilliantDragonBullet"        , 34    );
        SpellCardRegistry.registerSpellCard( THSC_GagoujiCyclone.class                , "thkaguyamod"    , "GagoujiCyclone"                , 40    );
        
    }
    
    public void registerDispenser()
    {
        BlockDispenser.dispenseBehaviorRegistry.putObject(THKaguyaItems.silver_knife, (IBehaviorDispenseItem) (blockSource, itemStack) -> {
               World world = blockSource.getWorld();//World
               IPosition iposition = BlockDispenser.func_149939_a(blockSource);//IPosition
               double x = iposition.getX();//
               double y = iposition.getY();//ディスペンサー射出口のところにある座標を取得
               double z = iposition.getZ();//
               Vec3 angle = Vec3.createVectorHelper(x - blockSource.getX(), y - blockSource.getY(), z - blockSource.getZ());
               THShotLib.getVectorNomalize(angle);

               EntitySilverKnife knife = new EntitySilverKnife(world, THShotLib.pos(x, y, z), angle, 0.58D, itemStack.getItemDamage());
               world.spawnEntityInWorld( knife );//ナイフを出す

               return itemStack.splitStack(itemStack.stackSize - 1);//アイテムをひとつ減らす
       });
        
        BlockDispenser.dispenseBehaviorRegistry.putObject(THKaguyaItems.shot_item, (IBehaviorDispenseItem) (blockSource, itemStack) -> {
               World world = blockSource.getWorld();//World
               IPosition iposition = BlockDispenser.func_149939_a(blockSource);//IPosition
               double x = iposition.getX();//
               double y = iposition.getY();//ディスペンサー射出口のところにある座標を取得
               double z = iposition.getZ();//
               Vec3 angle = Vec3.createVectorHelper(x - blockSource.getX(), y - blockSource.getY(), z - blockSource.getZ());
               THShotLib.getVectorNomalize(angle);

               EntityFamiliar living = new EntityFamiliar(world);

               ItemTHShot.shootDanmaku(itemStack, living, false, THShotLib.pos(blockSource.getX(), blockSource.getY(), blockSource.getZ()), angle, THShotLib.SIZE[ItemTHShot.shotTypeTrans[itemStack.getItemDamage()]] + 1.0D);

               boolean isInfinity = false;
               NBTTagCompound nbt = itemStack.getTagCompound();
               if(nbt != null)
               {
                   isInfinity = nbt.getBoolean("Infinity");
               }
               if(!isInfinity)
               {
                   itemStack.stackSize--;
               }
               //return itemStack.splitStack(itemStack);
               return itemStack;
       });
    }
    
    public void registerChestItem()
    {
        
    }
    
    public void registerDanmakuPattern()
    {
        DanmakuPatternRegistry.setDanmakuPattern();
    }

    public int addArmor(String armor)
    {
        return 0;
    }
    
    public int getNewRenderType()
    {
        return -1;
    }
}