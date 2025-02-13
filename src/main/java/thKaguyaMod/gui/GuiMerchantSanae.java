package thKaguyaMod.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import thKaguyaMod.init.THKaguyaItems;

@SideOnly(Side.CLIENT)
public class GuiMerchantSanae extends GuiContainer
{
    private static final Logger logger = LogManager.getLogger();
    private static final ResourceLocation field_147038_v = new ResourceLocation("textures/gui/container/villager.png");
    private IMerchant field_147037_w;
    private GuiMerchantSanae.MerchantButton field_147043_x;
    private GuiMerchantSanae.MerchantButton field_147042_y;
    private int field_147041_z;
    private String field_147040_A;
    private static final String __OBFID = "CL_00000762";

    public GuiMerchantSanae(EntityPlayer player, World par3World, String par4Str)
    {
        super(new ContainerMerchantSanae(player, par3World));
        //this.field_147037_w = par2IMerchant;
        this.field_147040_A = par4Str != null && par4Str.length() >= 1 ? par4Str : I18n.format("entity.Sanae.name", new Object[0]);
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    @Override
    public void initGui()
    {
        super.initGui();
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.buttonList.add(this.field_147043_x = new GuiMerchantSanae.MerchantButton(1, i + 120 + 27, j + 24 - 1, true));
        this.buttonList.add(this.field_147042_y = new GuiMerchantSanae.MerchantButton(2, i + 36 - 19, j + 24 - 1, false));
        this.field_147043_x.enabled = false;
        this.field_147042_y.enabled = false;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        this.fontRendererObj.drawString(this.field_147040_A, this.xSize / 2 - this.fontRendererObj.getStringWidth(this.field_147040_A) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Called from the main game loop to update the screen.
     */
    @Override
    public void updateScreen()
    {
        super.updateScreen();
        MerchantRecipeList merchantrecipelist = this.field_147037_w.getRecipes(this.mc.thePlayer);

        if (merchantrecipelist != null)
        {
            this.field_147043_x.enabled = this.field_147041_z < merchantrecipelist.size() - 1;
            this.field_147042_y.enabled = this.field_147041_z > 0;
        }
    }

    protected void actionPerformed(GuiButton p_146284_1_)
    {
        boolean flag = false;

        if (p_146284_1_ == this.field_147043_x)
        {
            ++this.field_147041_z;
            flag = true;
        }
        else if (p_146284_1_ == this.field_147042_y)
        {
            --this.field_147041_z;
            flag = true;
        }

        if (flag)
        {
            ((ContainerMerchant)this.inventorySlots).setCurrentRecipeIndex(this.field_147041_z);
            ByteBuf bytebuf = Unpooled.buffer();

            try
            {
                bytebuf.writeInt(this.field_147041_z);
                this.mc.getNetHandler().addToSendQueue(new C17PacketCustomPayload("MC|TrSel", bytebuf));
            }
            catch (Exception exception)
            {
                logger.error("Couldn\'t send trade info", exception);
            }
            finally
            {
                bytebuf.release();
            }
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(field_147038_v);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        MerchantRecipeList merchantrecipelist = this.field_147037_w.getRecipes(this.mc.thePlayer);

        if (merchantrecipelist != null && !merchantrecipelist.isEmpty())
        {
            int i1 = this.field_147041_z;
            MerchantRecipe merchantrecipe = (MerchantRecipe)merchantrecipelist.get(i1);

            if (merchantrecipe.isRecipeDisabled())
            {
                this.mc.getTextureManager().bindTexture(field_147038_v);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glDisable(GL11.GL_LIGHTING);
                this.drawTexturedModalRect(this.guiLeft + 83, this.guiTop + 21, 212, 0, 28, 21);
                this.drawTexturedModalRect(this.guiLeft + 83, this.guiTop + 51, 212, 0, 28, 21);
            }
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
        super.drawScreen(par1, par2, par3);
        MerchantRecipeList merchantrecipelist = this.field_147037_w.getRecipes(this.mc.thePlayer);

        if (merchantrecipelist != null && !merchantrecipelist.isEmpty())
        {
            int k = (this.width - this.xSize) / 2;
            int l = (this.height - this.ySize) / 2;
            int i1 = this.field_147041_z;
            MerchantRecipe merchantrecipe = (MerchantRecipe)merchantrecipelist.get(i1);
            GL11.glPushMatrix();
            ItemStack itemstack = new ItemStack(THKaguyaItems.aja_red_stone, 1);//merchantrecipe.getItemToBuy();
            ItemStack itemstack1 = merchantrecipe.getSecondItemToBuy();
            ItemStack itemstack2 = new ItemStack(THKaguyaItems.illness_recovery_charm, 5);//merchantrecipe.getItemToSell();
            RenderHelper.enableGUIStandardItemLighting();
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glEnable(GL11.GL_COLOR_MATERIAL);
            GL11.glEnable(GL11.GL_LIGHTING);
            itemRender.zLevel = 100.0F;
            itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack, k + 36, l + 24);
            itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack, k + 36, l + 24);

            if (itemstack1 != null)
            {
                itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack1, k + 62, l + 24);
                itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack1, k + 62, l + 24);
            }

            itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack2, k + 120, l + 24);
            itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack2, k + 120, l + 24);
            itemRender.zLevel = 0.0F;
            GL11.glDisable(GL11.GL_LIGHTING);

            if (this.func_146978_c(36, 24, 16, 16, par1, par2))
            {
                this.renderToolTip(itemstack, par1, par2);
            }
            else if (itemstack1 != null && this.func_146978_c(62, 24, 16, 16, par1, par2))
            {
                this.renderToolTip(itemstack1, par1, par2);
            }
            else if (this.func_146978_c(120, 24, 16, 16, par1, par2))
            {
                this.renderToolTip(itemstack2, par1, par2);
            }

            GL11.glPopMatrix();
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            RenderHelper.enableStandardItemLighting();
        }
    }

    public IMerchant func_147035_g()
    {
        return this.field_147037_w;
    }

    @SideOnly(Side.CLIENT)
    static class MerchantButton extends GuiButton
        {
            private final boolean field_146157_o;
            private static final String __OBFID = "CL_00000763";

            public MerchantButton(int par1, int par2, int par3, boolean par4)
            {
                super(par1, par2, par3, 12, 19, "");
                this.field_146157_o = par4;
            }

            /**
             * Draws this button to the screen.
             */
            public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_)
            {
                if (this.visible)
                {
                    p_146112_1_.getTextureManager().bindTexture(GuiMerchantSanae.field_147038_v);
                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                    boolean flag = p_146112_2_ >= this.xPosition && p_146112_3_ >= this.yPosition && p_146112_2_ < this.xPosition + this.width && p_146112_3_ < this.yPosition + this.height;
                    int k = 0;
                    int l = 176;

                    if (!this.enabled)
                    {
                        l += this.width * 2;
                    }
                    else if (flag)
                    {
                        l += this.width;
                    }

                    if (!this.field_146157_o)
                    {
                        k += this.height;
                    }

                    this.drawTexturedModalRect(this.xPosition, this.yPosition, l, k, this.width, this.height);
                }
            }
        }
}