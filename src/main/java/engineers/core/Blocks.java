package engineers.core;

import engineers.core.blocks.machines.charger.BlockCharger;
import engineers.core.blocks.machines.charger.GuiHandlerCharger;
import engineers.core.blocks.machines.charger.TileCharger;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Blocks {
	public static class Machines {
		public static class Charger {
			public static Block blockCharger;
			public static ItemBlock itemBlockCharger;

			private static void register() {
				blockCharger = new BlockCharger().setUnlocalizedName("blockCharger");
				blockCharger.setRegistryName("blockCharger");
				GameRegistry.register(blockCharger);

				itemBlockCharger = new ItemBlock(blockCharger);
				itemBlockCharger.setRegistryName(blockCharger.getRegistryName());
				GameRegistry.register(itemBlockCharger);

				GameRegistry.registerTileEntity(TileCharger.class, "tileCharger");

				NetworkRegistry.INSTANCE.registerGuiHandler(EngineersCore.instance, GuiHandlerRegistry.getInstance());
				GuiHandlerRegistry.getInstance().registerGuiHandler(new GuiHandlerCharger(), GuiHandlerCharger.getGuiID());
			}
		}

		public static void register() {
			Charger.register();
		}
	}
}
