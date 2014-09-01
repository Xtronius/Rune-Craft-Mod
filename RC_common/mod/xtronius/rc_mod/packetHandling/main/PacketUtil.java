package mod.xtronius.rc_mod.packetHandling.main;

import java.util.EnumMap;
import java.util.HashMap;

import mod.xtronius.rc_mod.packetHandling.packets.lvlInfo.PacketAttackExp;
import mod.xtronius.rc_mod.packetHandling.packets.lvlInfo.PacketAttackExpUNL;
import mod.xtronius.rc_mod.packetHandling.packets.lvlInfo.PacketAttackLvl;
import mod.xtronius.rc_mod.packetHandling.packets.lvlInfo.PacketDefenseExp;
import mod.xtronius.rc_mod.packetHandling.packets.lvlInfo.PacketDefenseExpUNL;
import mod.xtronius.rc_mod.packetHandling.packets.lvlInfo.PacketDefenseLvl;
import mod.xtronius.rc_mod.packetHandling.packets.lvlInfo.PacketFireMakingExp;
import mod.xtronius.rc_mod.packetHandling.packets.lvlInfo.PacketFireMakingExpUNL;
import mod.xtronius.rc_mod.packetHandling.packets.lvlInfo.PacketFireMakingLvl;
import mod.xtronius.rc_mod.packetHandling.packets.lvlInfo.PacketMiningExp;
import mod.xtronius.rc_mod.packetHandling.packets.lvlInfo.PacketMiningExpUNL;
import mod.xtronius.rc_mod.packetHandling.packets.lvlInfo.PacketMiningLvl;
import mod.xtronius.rc_mod.packetHandling.packets.lvlInfo.PacketStrengthExp;
import mod.xtronius.rc_mod.packetHandling.packets.lvlInfo.PacketStrengthExpUNL;
import mod.xtronius.rc_mod.packetHandling.packets.lvlInfo.PacketStrengthLvl;
import mod.xtronius.rc_mod.packetHandling.packets.lvlInfo.PacketWoodCuttingExp;
import mod.xtronius.rc_mod.packetHandling.packets.lvlInfo.PacketWoodCuttingExpUNL;
import mod.xtronius.rc_mod.packetHandling.packets.lvlInfo.PacketWoodCuttingLvl;
import mod.xtronius.rc_mod.util.enumClasses.EnumLvlInfo;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;

public class PacketUtil {
	
	private static HashMap<String, EnumMap<Side, FMLEmbeddedChannel>> packetMap =  new HashMap<String, EnumMap<Side, FMLEmbeddedChannel>>();
	
	public PacketUtil() {
		
		packetMap.put("WoodCutLvl", NetworkRegistry.INSTANCE.newChannel("WoodCutLvl", new ChannelHandler()));
		packetMap.put("MiningLvl", NetworkRegistry.INSTANCE.newChannel("MiningLvl", new ChannelHandler()));
		packetMap.put("AttackLvl", NetworkRegistry.INSTANCE.newChannel("AttackLvl", new ChannelHandler()));
		packetMap.put("StrengthLvl", NetworkRegistry.INSTANCE.newChannel("StrengthLvl", new ChannelHandler()));
		packetMap.put("DefenseLvl", NetworkRegistry.INSTANCE.newChannel("DefenseLvl", new ChannelHandler()));
		packetMap.put("FireMakingLvl", NetworkRegistry.INSTANCE.newChannel("FireMakingLvl", new ChannelHandler()));
		
		packetMap.put("WoodCutExp", NetworkRegistry.INSTANCE.newChannel("WoodCutExp", new ChannelHandler()));
		packetMap.put("MiningExp", NetworkRegistry.INSTANCE.newChannel("MiningExp", new ChannelHandler()));
		packetMap.put("AttackExp", NetworkRegistry.INSTANCE.newChannel("AttackExp", new ChannelHandler()));
		packetMap.put("StrengthExp", NetworkRegistry.INSTANCE.newChannel("StrengthExp", new ChannelHandler()));
		packetMap.put("DefenseExp", NetworkRegistry.INSTANCE.newChannel("DefenseExp", new ChannelHandler()));
		packetMap.put("FireMakingExp", NetworkRegistry.INSTANCE.newChannel("FireMakingExp", new ChannelHandler()));
		
		packetMap.put("WoodCutExpUNL", NetworkRegistry.INSTANCE.newChannel("WoodCutExpUNL", new ChannelHandler()));
		packetMap.put("MiningExpUNL", NetworkRegistry.INSTANCE.newChannel("MiningExpUNL", new ChannelHandler()));
		packetMap.put("AttackExpUNL", NetworkRegistry.INSTANCE.newChannel("AttackExpUNL", new ChannelHandler()));
		packetMap.put("StrengthExpUNL", NetworkRegistry.INSTANCE.newChannel("StrengthExpUNL", new ChannelHandler()));
		packetMap.put("DefenseExpUNL", NetworkRegistry.INSTANCE.newChannel("DefenseExpUNL", new ChannelHandler()));
		packetMap.put("FireMakingExpUNL", NetworkRegistry.INSTANCE.newChannel("FireMakingExpUNL", new ChannelHandler()));
		
		packetMap.put("RCBreakSpeed", NetworkRegistry.INSTANCE.newChannel("FireMakingExpUNL", new ChannelHandler()));
		packetMap.put("LogFire", NetworkRegistry.INSTANCE.newChannel("FireMakingExpUNL", new ChannelHandler()));
		packetMap.put("SwitchCombatStyle", NetworkRegistry.INSTANCE.newChannel("FireMakingExpUNL", new ChannelHandler()));
		packetMap.put("initCombatStyle", NetworkRegistry.INSTANCE.newChannel("FireMakingExpUNL", new ChannelHandler()));
		packetMap.put("bankTab", NetworkRegistry.INSTANCE.newChannel("FireMakingExpUNL", new ChannelHandler()));
		packetMap.put("bankScroll", NetworkRegistry.INSTANCE.newChannel("FireMakingExpUNL", new ChannelHandler()));
		
		packetMap.put("NullPrevPacket", NetworkRegistry.INSTANCE.newChannel("NullPrevPacket", new ChannelHandler()));
	}
	
	  public static EnumMap<Side, FMLEmbeddedChannel> getChannel(String name) { return packetMap.get(name) != null ? packetMap.get(name) : packetMap.get("NullPrevPacket"); }
	    
	    public static IPacket getPacketSkill(String lvlName, String type, float value) {
	    	EnumLvlInfo lvls = Enum.valueOf(EnumLvlInfo.class, lvlName);
			if(type.equals("Lvl")) {
				switch(lvls) {
					case WoodCutting: return new PacketWoodCuttingLvl((int)value); 
					case Mining: return new PacketMiningLvl((int)value); 
					case Attack: return new PacketAttackLvl((int)value); 
					case Strength: return new PacketStrengthLvl((int)value); 
					case Defense: return new PacketDefenseLvl((int)value); 
					case FireMaking: return new PacketFireMakingLvl((int)value);
				}
			}
			else if(type.equals("Exp")) {
				switch(lvls) {
					case WoodCutting: return new PacketWoodCuttingExp(value); 
					case Mining: return new PacketMiningExp(value); 
					case Attack: return new PacketAttackExp(value);
					case Strength: return new PacketStrengthExp(value);
					case Defense: return new PacketDefenseExp(value);
					case FireMaking: return new PacketFireMakingExp(value);
				}
			}
			else if(type.equals("ExpUNL")) {
				switch(lvls) {
					case WoodCutting: return new PacketWoodCuttingExpUNL(value); 
					case Mining: return new PacketMiningExpUNL(value); 
					case Attack: return new PacketAttackExpUNL(value); 
					case Strength: return new PacketStrengthExpUNL(value); 
					case Defense: return new PacketDefenseExpUNL(value);
					case FireMaking: return new PacketFireMakingExpUNL(value); 
				}
			}
			return null;
		}
}
