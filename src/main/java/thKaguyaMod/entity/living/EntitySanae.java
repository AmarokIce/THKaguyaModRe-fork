package thKaguyaMod.entity.living;

import net.minecraft.block.Block;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Tuple;
import net.minecraft.util.Vec3;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.spellcard.EntitySpellCard;
import thKaguyaMod.init.THKaguyaItems;
import thKaguyaMod.item.ItemTHShot;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 早苗さん！早苗さん！
 */
public class EntitySanae extends EntityDanmakuMob implements IDanmakuMob, IMerchant
{
	
	private EntityPlayer buyingPlayer;
	private MerchantRecipeList buyingList;
	private float field_82191_bN;
	public static final Map villagersSellingList = new HashMap();
	
    public EntitySanae(World world)
    {
        super(world);
        
        this.setSize(0.6F, 1.7F);//MOBの当たり判定の大きさ 横奥行き、高さ、大きさ
        
    	experienceValue = 760;//経験値の量
    	
    	this.setDanmakuMobName("Sanae Kochiya");
    	this.setSpecies(this.SPECIES_HUMAN, this.SPECIES_GOD);
    	
    	this.setDanmakuPattern(NOT_ATTACK);
    	this.setMaxHP(56.0F);
    	this.setHealth(56.0F);
    	this.setSpeed(0.5D);
    	this.setAttackDistance(14.0D);
    	this.setDetectionDistance(0.0D);
    	this.setFlyingHeight(0);
    	this.isFlyingMode = false;
    	
    	this.isSpellCardMode = false;
    	this.attackInterval = 0;
    }
    
	//Entityの初期処理
	@Override
    protected void entityInit()
    {
        super.entityInit();
        buyingList = new MerchantRecipeList();
        buyingList.add(new MerchantRecipe( new ItemStack(THKaguyaItems.shot_material, 64, 0),
				new ItemStack(THKaguyaItems.illness_recovery_charm, 1, 0)));
    }
    
    /**
     * 使用しているスペルカードNoを返す
     * @return 使用しているスペルカードNo
     */
    @Override
    public int getUsingSpellCardNo()
    {
    	switch(getDanmakuPattern())
    	{
    	case SPELLCARD_ATTACK01:
    		return EntitySpellCard.SC_SANAE_MiracleFruit;
    	case SPELLCARD_ATTACK02:
    		return EntitySpellCard.SC_SANAE_Moses_no_Kiseki;
    	case SPELLCARD_ATTACK03:
    		return EntitySpellCard.SC_SANAE_Yasaka_no_Kamikaze;
    	case SPELLCARD_ATTACK04:
    		return EntitySpellCard.SC_SANAE_YouryokuSpoiler;
    	default:
    		return -1;
    	}
    }
    
    //死んでいるときに呼ばれる
    @Override
    protected void onDeathUpdate()
    {
    	if(ticksExisted <= lastTime)
    	{
    		return;
    	}
    	//this.addDefaultEquipmentAndRecipies(1);
    	
    	switch(getDanmakuPattern())
    	{
    		case NORMAL_ATTACK01:
    			setFlyingHeight(2);
    			moveDanmakuAttack(SPELLCARD_ATTACK01, 40, 60.0F, 160);
    			break;
    		case SPELLCARD_ATTACK01:
    			moveDanmakuAttack(ATTACK_END, 90, 40.0F, 160);
    			break;
    		case SPELLCARD_ATTACK02:
    			moveDanmakuAttack(ATTACK_END, 90, 40.0F, 160);
    			break;
    		case SPELLCARD_ATTACK03:
    			moveDanmakuAttack(ATTACK_END, 90, 40.0F, 160);
    			break;
    		case SPELLCARD_ATTACK04:
    			moveDanmakuAttack(ATTACK_END, 90, 40.0F, 160);
    			break;
    		default:
    			if(deathTime % 6 == 0)
    			{
    				THShotLib.explosionEffect(worldObj, posX, posY, posZ, 1.0F + deathTime * 0.1F);
    			}
    			super.onDeathUpdate();
    			break;
    	}
    }
    
    @Override
    public void danmakuPattern(int level)
    {
    	Vec3 look = this.getLookVec();
    	switch(getDanmakuPattern())
    	{
    		case NORMAL_ATTACK01:
    			danmaku01(look, level);
    			break;
    		case SPELLCARD_ATTACK01:
    			if(attackCounter == 1)
    			{
    				this.useSpellCard(EntitySpellCard.SC_SANAE_MiracleFruit);
    			}
    			else if(attackCounter >= 140)
    			{
    				attackCounter = 0;
    			}
    			break;
    		case SPELLCARD_ATTACK02:
    			if(attackCounter == 1)
    			{
    				this.useSpellCard(EntitySpellCard.SC_SANAE_Moses_no_Kiseki);
    			}
    			else if(attackCounter >= 140)
    			{
    				attackCounter = 0;
    			}
    			break;
    		case SPELLCARD_ATTACK03:
    			if(attackCounter == 1)
    			{
    				this.useSpellCard(EntitySpellCard.SC_SANAE_Yasaka_no_Kamikaze);
    			}
    			else if(attackCounter >= 140)
    			{
    				attackCounter = 0;
    			}
    			break;
    		case SPELLCARD_ATTACK04:
    			if(attackCounter == 1)
    			{
    				this.useSpellCard(EntitySpellCard.SC_SANAE_YouryokuSpoiler);
    			}
    			else if(attackCounter >= 140)
    			{
    				attackCounter = 0;
    			}
    			break;
    		default:
    			break;
    	}
    }
    
    private void  danmaku01(Vec3 look, int level)
    {
    	if(attackCounter < 50)
    	{
    		setStopStart();
    		double size = 2.0D;
    		int div = 10;
    		double divLength = size / (double)div;
    		
    		Vec3 pos = THShotLib.pos(Math.cos(Math.PI / 2.0D) * size, Math.sin(Math.PI / 2.0D) * size, 0.0D);
    		Vec3 pos2 = pos;
    		
    		float angle = 252F / 180F * 3.141593F;
    		
    		for(int i = 0; i < attackCounter; i++)
    		{
    			pos.xCoord += Math.cos(angle) * divLength;
    			pos.yCoord += Math.sin(angle) * divLength;
    			
    			if((i % div) == (div - 1))
    			{
    				angle += 144F / 180F * 3.141593F;
    			}
    		}
    		pos = THShotLib.getOuterProduct(look, pos);
    		//THShotLib.get
    		//pos.xCoord = pos.xCoord * look.xCoord;
    		//pos.yCoord = pos.yCoord * look.yCoord;
    		//pos.zCoord = pos.zCoord * look.zCoord;
    		pos.xCoord += posX;
    		pos.yCoord += THShotLib.getPosYFromEye(this);
    		pos.zCoord += posZ;
    		
    		THShotLib.createShot(this, this, pos, look, 0F, 0.0F, 0.5F, 0.03F, gravity_Zero(), ShotData.shot(THShotLib.FORM_TINY, THShotLib.BLUE, 55 - attackCounter, 120));
    	}
    	else if(attackCounter == 50)
    	{
    		setStopEnd();
    	}
    	else if(attackCounter >= 100)
    	{
    		attackCounter = 0;
    	}
    }
    
	@Override
    protected Item getDropItem()
    {
    	return THKaguyaItems.power_item;
    }
	
	//倒れたときに落とすアイテム
	@Override
	protected void dropFewItems(boolean hasBeenAttackedByPlayer, int lootingLevel)
    {
		super.dropFewItems(hasBeenAttackedByPlayer, lootingLevel);
		
		if(hasBeenAttackedByPlayer  && getUsingSpellCardNo() != -1)
		{	
			
			this.dropShotItem(ItemTHShot.STAR, 16 + rand.nextInt(2) + lootingLevel, 5, 32, THShotLib.GREEN, 0, THShotLib.BOUND02, 2);
	        
		    this.dropPowerUpItem(25 + this.rand.nextInt(5) + this.rand.nextInt(1 + lootingLevel));
	
	        this.dropPointItem(25 + this.rand.nextInt(5) + this.rand.nextInt(1 + lootingLevel));
		}
    }
	
    //保存したデータを読み込む
    @Override
    public void readEntityFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readEntityFromNBT(nbtTagCompound);
        if (nbtTagCompound.hasKey("Offers", 10))
        {
            NBTTagCompound nbttagcompound1 = nbtTagCompound.getCompoundTag("Offers");
            this.buyingList = new MerchantRecipeList(nbttagcompound1);
        }
    }

    //保存するデータを書き込む
    @Override
    public void writeEntityToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeEntityToNBT(nbtTagCompound);
        if (this.buyingList != null)
        {
            nbtTagCompound.setTag("Offers", this.buyingList.getRecipiesAsTags());
        }
    }
    
	//自然スポーンするときに呼ばれる。tureならスポーンする
	@Override
    public boolean getCanSpawnHere()
    {
		if(super.getCanSpawnHere() == false)
		{
			return false;
		}
		
    	if(rand.nextInt(100) < 98)
    	{
    		return false;
    	}
    	
    	int yPosition = MathHelper.floor_double(this.boundingBox.minY);
    	int xPosition = MathHelper.floor_double(this.posX);
        int zPosition = MathHelper.floor_double(this.posZ);
    	Block pointBlock = worldObj.getBlock(xPosition, yPosition - 1, zPosition);
    	//地面が草ブロックか土ブロックか砂ブロックならスポーンする
    	//if(pointBlock == Block.grass.blockID || pointBlock == Block.dirt.blockID || pointBlock == Block.sand.blockID)
    	if( 	worldObj.getBlock(xPosition - 1, yPosition - 1, zPosition) == pointBlock &&
    			worldObj.getBlock(xPosition + 1, yPosition - 1, zPosition) == pointBlock &&
    			worldObj.getBlock(xPosition, yPosition - 1, zPosition - 1) == pointBlock &&
    			worldObj.getBlock(xPosition, yPosition - 1, zPosition + 1) == pointBlock)
    	{
    		return false;
    	}
    	
    	return true;
    }
	
    //無敵かどうかを返す
    @Override
    public boolean isEntityInvulnerable()
    {
    	if(getDanmakuPattern() == NOT_ATTACK)
    	{
    		return true;
    	}
    	return false;
    }
	
    /**
     * 右クリックをされたときの処理
     */
    public boolean interact(EntityPlayer player)
    {
        ItemStack itemstack = player.inventory.getCurrentItem();
        boolean flag = itemstack != null && itemstack.getItem() == Items.spawn_egg;

        if (!flag && this.isEntityAlive() && /*!this.isTrading() && !this.isChild() &&*/ !player.isSneaking())
        {
            if (!this.worldObj.isRemote)
            {
                this.setCustomer(player);
                player.displayGUIMerchant(this, StatCollector.translateToLocal("entity.Sanae.name"));
                //player.openGui(THKaguyaCore.instance, THKaguyaCore.instance.guiMerchantSanaeId, worldObj, (int)posX, (int)posY, (int)posZ);
            }

            return true;
        }
        else
        {
            return super.interact(player);
        }
    }
    
    public void setCustomer(EntityPlayer player)
    {
        this.buyingPlayer = player;
    }

	@Override
	public EntityPlayer getCustomer() {
		return this.buyingPlayer;
	}

	@Override
	public MerchantRecipeList getRecipes(EntityPlayer var1) {
		return this.buyingList;
	}

	@Override
	public void setRecipes(MerchantRecipeList merchantRecipeList) {
		//merchantRecipeList.
		
	}

    public void useRecipe(MerchantRecipe par1MerchantRecipe)
    {
        par1MerchantRecipe.incrementToolUses();
        this.livingSoundTime = -this.getTalkInterval();
       // this.playSound("mob.villager.yes", this.getSoundVolume(), this.getSoundPitch());

        if (par1MerchantRecipe.hasSameIDsAs((MerchantRecipe)this.buyingList.get(this.buyingList.size() - 1)))
        {
            /*this.timeUntilReset = 40;
            this.needsInitilization = true;

            if (this.buyingPlayer != null)
            {
                this.lastBuyingPlayer = this.buyingPlayer.getCommandSenderName();
            }
            else
            {
                this.lastBuyingPlayer = null;
            }*/
        }

        if (par1MerchantRecipe.getItemToBuy().getItem() == Items.emerald)
        {
            //this.wealth += par1MerchantRecipe.getItemToBuy().stackSize;
        }
    }

    public void func_110297_a_(ItemStack par1ItemStack)
    {
        if (!this.worldObj.isRemote && this.livingSoundTime > -this.getTalkInterval() + 20)
        {
            this.livingSoundTime = -this.getTalkInterval();

            /*if (par1ItemStack != null)
            {
                this.playSound("mob.villager.yes", this.getSoundVolume(), this.getSoundPitch());
            }
            else
            {
                this.playSound("mob.villager.no", this.getSoundVolume(), this.getSoundPitch());
            }*/
        }
    }
    
    /**
     * based on the villagers profession add items, equipment, and recipies adds par1 random items to the list of things
     * that the villager wants to buy. (at most 1 of each wanted type is added)
     */
    private void addDefaultEquipmentAndRecipies(int par1)
    {
        if (this.buyingList != null)
        {
            this.field_82191_bN = MathHelper.sqrt_float((float)this.buyingList.size()) * 0.2F;
        }
        else
        {
            this.field_82191_bN = 0.0F;
        }

        MerchantRecipeList merchantrecipelist;
        merchantrecipelist = new MerchantRecipeList();
        //VillagerRegistry.manageVillagerTrades(merchantrecipelist, this, 0, this.rand);
        int k;
        //label50:

                func_146091_a(merchantrecipelist, Items.wheat, this.rand, this.adjustProbability(0.9F));
                func_146091_a(merchantrecipelist, Item.getItemFromBlock(Blocks.wool), this.rand, this.adjustProbability(0.5F));

        /*if (merchantrecipelist.isEmpty())
        {
            func_146091_a(merchantrecipelist, Items.gold_ingot, this.rand, 1.0F);
        }*/

        Collections.shuffle(merchantrecipelist);

        if (this.buyingList == null)
        {
            this.buyingList = new MerchantRecipeList();
        }

        for (int l = 0; l < par1 && l < merchantrecipelist.size(); ++l)
        {
            this.buyingList.addToListWithCheck((MerchantRecipe)merchantrecipelist.get(l));
        }
    }
    
    public static void func_146091_a(MerchantRecipeList p_146091_0_, Item p_146091_1_, Random p_146091_2_, float p_146091_3_)
    {
        if (p_146091_2_.nextFloat() < p_146091_3_)
        {
            p_146091_0_.add(new MerchantRecipe(func_146088_a(p_146091_1_, p_146091_2_), Items.emerald));
        }
    }
    
    private static ItemStack func_146088_a(Item p_146088_0_, Random p_146088_1_)
    {
        return new ItemStack(p_146088_0_, func_146092_b(p_146088_0_, p_146088_1_), 0);
    }
    
    private static int func_146092_b(Item p_146092_0_, Random p_146092_1_)
    {
        Tuple tuple = (Tuple)villagersSellingList.get(p_146092_0_);
        return tuple == null ? 1 : (((Integer)tuple.getFirst()).intValue() >= ((Integer)tuple.getSecond()).intValue() ? ((Integer)tuple.getFirst()).intValue() : ((Integer)tuple.getFirst()).intValue() + p_146092_1_.nextInt(((Integer)tuple.getSecond()).intValue() - ((Integer)tuple.getFirst()).intValue()));
    }
    
    /**
     * Adjusts the probability of obtaining a given recipe being offered by a villager
     */
    private float adjustProbability(float par1)
    {
        float f1 = par1 + this.field_82191_bN;
        return f1 > 0.9F ? 0.9F - (f1 - 0.9F) : f1;
    }
    
    static
    {
        villagersSellingList.put(Items.coal, new Tuple(Integer.valueOf(16), Integer.valueOf(24)));
        villagersSellingList.put(Items.iron_ingot, new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
        villagersSellingList.put(Items.gold_ingot, new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
        villagersSellingList.put(Items.diamond, new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        villagersSellingList.put(Items.paper, new Tuple(Integer.valueOf(24), Integer.valueOf(36)));
        villagersSellingList.put(Items.book, new Tuple(Integer.valueOf(11), Integer.valueOf(13)));
    }
}