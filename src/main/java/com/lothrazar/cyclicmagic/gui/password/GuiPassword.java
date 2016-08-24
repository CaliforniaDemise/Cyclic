package com.lothrazar.cyclicmagic.gui.password;
import java.io.IOException;
import com.lothrazar.cyclicmagic.ModMain;
import com.lothrazar.cyclicmagic.block.tileentity.TileEntityPassword;
import com.lothrazar.cyclicmagic.net.PacketTilePassword;
import com.lothrazar.cyclicmagic.util.Const;
import com.lothrazar.cyclicmagic.util.UtilChat;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiPassword extends GuiContainer {
  private static final ResourceLocation table = new ResourceLocation(Const.MODID, "textures/gui/password.png");
  private TileEntityPassword tile;
  private GuiTextField txtPassword;
  public GuiPassword(InventoryPlayer inventoryPlayer, TileEntityPassword tileEntity) {
    super(new ContainerPassword(inventoryPlayer, tileEntity));
    tile = tileEntity;
    this.ySize = 79;//texture size in pixels
//    this.height = this.ySize;
  }
  @SideOnly(Side.CLIENT)
  @Override
  protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
    super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    String s = UtilChat.lang("tile.password_block.name");
    this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
    if (txtPassword != null) {
      txtPassword.drawTextBox();
    }
  }
  @Override
  public void initGui() {
    super.initGui();
    int width = 120, height = 20;
    txtPassword = new GuiTextField(0, this.fontRendererObj, (xSize / 2 - width / 2) + 5, 20 + (height / 2), 127, height); // -
    // new GuiTextField(1, this.fontRendererObj, this.width / 2 - w,20, w, h);
    txtPassword.setMaxStringLength(40);
    System.out.println("SET GUI TEXT "+tile.getMyPassword());
    txtPassword.setText(tile.getMyPassword());
    txtPassword.setFocused(true);
  }
//  @Override
//  public void onGuiClosed() {
//    super.onGuiClosed();
//   this.tile.setMyPassword(txtPassword.getText());
//    ModMain.network.sendToServer(new PacketTilePassword(txtPassword.getText(), tile.getPos()));
//  }
  @Override
  protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    this.mc.getTextureManager().bindTexture(table);
    int thisX = (this.width - this.xSize) / 2;
    int thisY = (this.height - this.ySize) / 2;
    int texture_width = 176;
    int texture_height = 166;
    int u = 0, v = 0;
    Gui.drawModalRectWithCustomSizedTexture(thisX, thisY, u, v, this.xSize, this.ySize, texture_width, texture_height);
  }
  //TODO: Base gui class for hasTextbox
  // http://www.minecraftforge.net/forum/index.php?topic=22378.0
  // below is all the stuff that makes the text box NOT broken
  //TODO: array list of text boxes??
  @Override
  public void updateScreen() {
    super.updateScreen();
    if (txtPassword != null) {
      txtPassword.updateCursorCounter();
    }
  }
  @Override
  protected void keyTyped(char par1, int par2) throws IOException {
    super.keyTyped(par1, par2);
    if (txtPassword != null && txtPassword.isFocused()) {
      txtPassword.textboxKeyTyped(par1, par2);
      
      ModMain.network.sendToServer(new PacketTilePassword(txtPassword.getText(), tile.getPos()));
    }
  }
  @Override
  protected void mouseClicked(int x, int y, int btn) throws IOException {
    super.mouseClicked(x, y, btn);// x/y pos is 33/30
    if (txtPassword != null) {
      txtPassword.mouseClicked(x, y, btn);
      txtPassword.setFocused(true);
    }
  }
  // ok end of textbox fixing stuff
}
