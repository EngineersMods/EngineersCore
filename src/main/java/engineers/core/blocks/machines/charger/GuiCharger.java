package engineers.core.blocks.machines.charger;

import java.awt.Color;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiCharger extends GuiContainer {

	// This is the resource location for the background image
	private static final ResourceLocation texture = new ResourceLocation(
			"engineerscore:textures/gui/charger.png");
	private TileCharger tileEntity;

	public GuiCharger(InventoryPlayer invPlayer, TileCharger tileInventoryFurnace) {
		super(new ContainerCharger(invPlayer, tileInventoryFurnace));

		// Set the width and height of the gui
		xSize = 176;
		ySize = 207;

		this.tileEntity = tileInventoryFurnace;
	}

	// some [x,y] coordinates of graphical elements
	final int COOK_BAR_XPOS = 49;
	final int COOK_BAR_YPOS = 60;
	final int COOK_BAR_ICON_U = 0; // texture position of white arrow icon
	final int COOK_BAR_ICON_V = 207;
	final int COOK_BAR_WIDTH = 80;
	final int COOK_BAR_HEIGHT = 17;

	final int FLAME_XPOS = 54;
	final int FLAME_YPOS = 80;
	final int FLAME_ICON_U = 176; // texture position of flame icon
	final int FLAME_ICON_V = 0;
	final int FLAME_WIDTH = 14;
	final int FLAME_HEIGHT = 14;
	final int FLAME_X_SPACING = 18;

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {
		// Bind the image texture
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		// Draw the image
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		// get cook progress as a double between 0 and 1
		double power = tileEntity.fractionOfPowerFull();
		// draw the cook progress bar
		drawTexturedModalRect(guiLeft + COOK_BAR_XPOS, guiTop + COOK_BAR_YPOS, COOK_BAR_ICON_U, COOK_BAR_ICON_V,
				(int) (power * COOK_BAR_WIDTH), COOK_BAR_HEIGHT);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);

		final int LABEL_XPOS = 5;
		final int LABEL_YPOS = 5;
		fontRendererObj.drawString(tileEntity.getDisplayName().getUnformattedText(), LABEL_XPOS, LABEL_YPOS,
				Color.darkGray.getRGB());

		List<String> hoveringText = new ArrayList<String>();

		// If the mouse is over the progress bar add the progress bar hovering
		// text
		if (isInRect(guiLeft + COOK_BAR_XPOS, guiTop + COOK_BAR_YPOS, COOK_BAR_WIDTH, COOK_BAR_HEIGHT, mouseX,
				mouseY)) {
			hoveringText.add(formatInt(tileEntity.getPower()) + "/" + formatInt(tileEntity.getMaxPower()));
			int cookPercentage = (int) (tileEntity.fractionOfPowerFull() * 100);
			hoveringText.add(cookPercentage + "%");
		}

		// If hoveringText is not empty draw the hovering text
		if (!hoveringText.isEmpty()) {
			drawHoveringText(hoveringText, mouseX - guiLeft, mouseY - guiTop, fontRendererObj);
		}
		// // You must re bind the texture and reset the colour if you still
		// need to use it after drawing a string
		// Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		// GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		renderPowerBar(k, l);
	}

	// Returns true if the given x,y coordinates are within the given rectangle
	public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
		return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
	}

	public void renderPowerBar(int k, int l) {
		// int power = tileEntity.getPower();
		// double percent = tileEntity.fractionOfPowerFull();
		// int top = 14;
		// int bottom = top + 42;
		// int height = (int) (top * percent);
		// System.out.println(height);
		// drawGradientRect(15, height, 25, bottom, Color.MAGENTA.getRGB(),
		// Color.BLUE.getRGB());

	}

	public String formatInt(int _int) {
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
		return numberFormat.format(_int);
	}
}