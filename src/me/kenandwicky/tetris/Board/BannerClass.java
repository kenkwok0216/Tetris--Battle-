package me.kenandwicky.tetris.Board;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;

public class BannerClass {
	
	CreateBanner banner = new CreateBanner();
	
	public ItemStack Make(char c) {
		return banner.WordOfBanner(c);
	}
}

//delegate
class CreateBanner {
	
	public ItemStack WordOfBanner(char c) {
		if (c == 'A') {
			return ACreator.Create();
		} else if (c == 'B') {
			return BCreator.Create();
		} else if (c == 'C') {
			return CCreator.Create();
		} else if (c == 'D') {
			return DCreator.Create();
		} else if (c == 'E') {
			return ECreator.Create();
		} else if (c == 'F') {
			return FCreator.Create();
		} else if (c == 'G') {
			return GCreator.Create();
		} else if (c == 'H') {
			return HCreator.Create();
		} else if (c == 'I') {
			return ICreator.Create();
		} else if (c == 'J') {
			return JCreator.Create();
		} else if (c == 'K') {
			return KCreator.Create();
		} else if (c == 'L') {
			return LCreator.Create();
		} else if (c == 'M') {
			return MCreator.Create();
		} else if (c == 'N') {
			return NCreator.Create();
		} else if (c == 'O') {
			return OCreator.Create();
		} else if (c == 'P') {
			return PCreator.Create();
		} else if (c == 'Q') {
			return QCreator.Create();
		} else if (c == 'R') {
			return RCreator.Create();
		} else if (c == 'S') {
			return SCreator.Create();
		} else if (c == 'T') {
			return TCreator.Create();
		} else if (c == 'U') {
			return UCreator.Create();
		} else if (c == 'V') {
			return VCreator.Create();
		} else if (c == 'W') {
			return WCreator.Create();
		} else if (c == 'X') {
			return XCreator.Create();
		} else if (c == 'Y') {
			return YCreator.Create();
		} else if (c == 'Z') {
			return ZCreator.Create();
		} else if (c == '1') {
			return OneCreator.Create();
		} else if (c == '2') {
			return TwoCreator.Create();
		} else if (c == '3') {
			return ThreeCreator.Create();
		} else if (c == '4') {
			return FourCreator.Create();
		} else if (c == '5') {
			return FiveCreator.Create();
		} else if (c == '6') {
			return SixCreator.Create();
		} else if (c == '7') {
			return SevenCreator.Create();
		} else if (c == '8') {
			return EightCreator.Create();
		} else if (c == '9') {
			return NineCreator.Create();
		} else if (c == '0') {
			return ZeroCreator.Create();
		} else if (c == '_') {
			return UnderscoreCreator.Create();
		} else if (c == ':') {
			return ColonCreator.Create();
		} else {
		return new ItemStack(Material.WHITE_WALL_BANNER, 1);
		}
	}	
}

//delegators
class ACreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_RIGHT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_MIDDLE));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		
		m.setPatterns(patterns);
		m.setDisplayName("Banner A");
		
		i.setItemMeta(m);
		
		return i;		
	}
}

class BCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_RIGHT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.CURLY_BORDER));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_MIDDLE));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		
		m.setPatterns(patterns);
		m.setDisplayName("Banner B");
		
		i.setItemMeta(m);
		
		return i;		
	}
}

class CCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_RIGHT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_MIDDLE));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner C");
		
		i.setItemMeta(m);
		
		return i;		
	}
}

class DCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_RIGHT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.CURLY_BORDER));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner D");
		i.setItemMeta(m);
		
		return i;		
	}
}

class ECreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_MIDDLE));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner E");
		i.setItemMeta(m);
		
		return i;		
	}
}

class FCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_MIDDLE));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_RIGHT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner F");
		i.setItemMeta(m);
		
		return i;		
	}
}

class GCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_RIGHT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.HALF_HORIZONTAL));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner G");
		i.setItemMeta(m);
		
		return i;		
	}
}

class HCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.BLACK_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_TOP));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_BOTTOM));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_RIGHT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner H");
		i.setItemMeta(m);
		
		return i;		
	}
}

class ICreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_CENTER));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner I");
		i.setItemMeta(m);
		
		return i;		
	}
}

class JCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.HALF_HORIZONTAL));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_RIGHT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner J");
		i.setItemMeta(m);
		
		return i;		
	}
}

class KCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_DOWNRIGHT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.HALF_HORIZONTAL));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_DOWNLEFT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner K");
		i.setItemMeta(m);
		
		return i;		
	}
}

class LCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner L");
		i.setItemMeta(m);
		
		return i;		
	}
}

class MCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.TRIANGLE_TOP));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.TRIANGLES_TOP));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_RIGHT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner M");
		i.setItemMeta(m);
		
		return i;		
	}
}

class NCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.TRIANGLE_TOP));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_DOWNRIGHT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_RIGHT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner N");
		i.setItemMeta(m);
		
		return i;		
	}
}

class OCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_RIGHT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner O");
		i.setItemMeta(m);
		
		return i;		
	}
}

class PCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_RIGHT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.HALF_HORIZONTAL_MIRROR));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_MIDDLE));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner P");
		i.setItemMeta(m);
		
		return i;		
	}
}

class QCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.BLACK_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.RHOMBUS_MIDDLE));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_RIGHT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.SQUARE_BOTTOM_RIGHT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner Q");
		i.setItemMeta(m);
		
		return i;		
	}
}

class RCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.HALF_HORIZONTAL));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_CENTER));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_DOWNRIGHT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner R");
		i.setItemMeta(m);
		
		return i;		
	}
}

class SCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.BLACK_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.RHOMBUS_MIDDLE));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_MIDDLE));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_DOWNRIGHT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner S");
		i.setItemMeta(m);
		
		return i;		
	}
}

class TCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_CENTER));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner T");
		i.setItemMeta(m);
		
		return i;		
	}
}

class UCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_RIGHT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner U");
		i.setItemMeta(m);
		
		return i;		
	}
}

class VCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_DOWNLEFT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.TRIANGLE_BOTTOM));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_DOWNLEFT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner V");
		i.setItemMeta(m);
		
		return i;		
	}
}

class WCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.TRIANGLE_BOTTOM));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.TRIANGLES_BOTTOM));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_RIGHT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner W");
		i.setItemMeta(m);
		
		return i;		
	}
}

class XCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.CROSS));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner X");
		i.setItemMeta(m);
		
		return i;		
	}
}

class YCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_DOWNRIGHT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.HALF_HORIZONTAL_MIRROR));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_DOWNLEFT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner Y");
		i.setItemMeta(m);
		
		return i;		
	}
}

class ZCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_DOWNLEFT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner Z");
		i.setItemMeta(m);
		
		return i;		
	}
}

class OneCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_CENTER));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.SQUARE_TOP_LEFT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.CURLY_BORDER));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner 1");
		i.setItemMeta(m);
		
		return i;		
	}
}

class TwoCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.RHOMBUS_MIDDLE));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_DOWNLEFT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner 2");
		i.setItemMeta(m);
		
		return i;		
	}
}

class ThreeCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_MIDDLE));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.CURLY_BORDER));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_RIGHT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner 3");
		i.setItemMeta(m);
		
		return i;		
	}
}

class FourCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.HALF_HORIZONTAL_MIRROR));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_RIGHT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_MIDDLE));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner 4");
		i.setItemMeta(m);
		
		return i;		
	}
}

class FiveCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.RHOMBUS_MIDDLE));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_DOWNRIGHT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner 5");
		i.setItemMeta(m);
		
		return i;		
	}
}

class SixCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_RIGHT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.HALF_HORIZONTAL));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_MIDDLE));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner 6");
		i.setItemMeta(m);
		
		return i;		
	}
}

class SevenCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_DOWNLEFT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner 7");
		i.setItemMeta(m);
		
		return i;		
	}
}

class EightCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_MIDDLE));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_RIGHT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner 8");
		i.setItemMeta(m);
		
		return i;		
	}
}

class NineCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.HALF_HORIZONTAL_MIRROR));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_MIDDLE));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_RIGHT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner 9");
		i.setItemMeta(m);
		
		return i;		
	}
}

class ZeroCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_RIGHT));
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_DOWNLEFT));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		m.setPatterns(patterns);
		m.setDisplayName("Banner 0");
		i.setItemMeta(m);
		
		return i;		
	}
}

class UnderscoreCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_WALL_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.BORDER));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.CROSS));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.DIAGONAL_RIGHT_MIRROR));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.DIAGONAL_LEFT));
		m.setPatterns(patterns);
		m.setDisplayName("Banner Underscore");
		i.setItemMeta(m);
		
		return i;		
	}
}

class ColonCreator {
	static ItemStack Create() {
		ItemStack i = new ItemStack(Material.WHITE_BANNER, 1);
		BannerMeta m = (BannerMeta)i.getItemMeta();
		
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_CENTER));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_MIDDLE));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_SMALL));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_BOTTOM));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
		patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_TOP));
		m.setPatterns(patterns);
		m.setDisplayName("Banner Colon");
		i.setItemMeta(m);
		
		return i;		
	}
}

