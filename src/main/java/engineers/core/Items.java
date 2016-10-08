package engineers.core;

import engineers.core.items.batteries.ItemBattery;
import engineers.core.items.batteries.ItemBattery.BatteryTypes;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class Items {
	public static final class Batteries {
		public static Item batteryBasic, batteryAdvanced, batteryFuturistic;

		public static void register() {
			batteryBasic = new ItemBattery(BatteryTypes.BASIC);
			batteryAdvanced = new ItemBattery(BatteryTypes.ADVANCED);
			batteryFuturistic = new ItemBattery(BatteryTypes.FUTURISTIC);
		}

		public static void registerClient() {
			ModelLoader.setCustomModelResourceLocation(batteryBasic, 0,
					new ModelResourceLocation(EngineersCore.appendID("battery_basic")));
			ModelLoader.setCustomModelResourceLocation(batteryAdvanced, 0,
					new ModelResourceLocation(EngineersCore.appendID("battery_advanced")));
			ModelLoader.setCustomModelResourceLocation(batteryFuturistic, 0,
					new ModelResourceLocation(EngineersCore.appendID("battery_futuristic")));
		}
	}
}
