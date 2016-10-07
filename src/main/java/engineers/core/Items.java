package engineers.core;

import engineers.core.items.batteries.ItemBattery;
import engineers.core.items.batteries.ItemBattery.BatteryTypes;
import net.minecraft.item.Item;

public class Items {
	public static final class Batteries {
		public static Item batteryLow, batteryAdvanced, batteryFuturistic;

		public static void register() {
			batteryLow = new ItemBattery(BatteryTypes.BASIC);
			batteryAdvanced = new ItemBattery(BatteryTypes.ADVANCED);
			batteryFuturistic = new ItemBattery(BatteryTypes.FUTURISTIC);
		}
		
		public static void registerClient(){
			
		}
	}
}
