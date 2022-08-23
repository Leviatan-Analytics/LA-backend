package com.leviatan.backend.model.league;

public enum Champion {
    AATROX("Atrox"),
    AHRI("Ahri"),
    AKALI("Akali"),
    AKSHAN("Akshan"),
    ALISTAR("Alistar"),
    AMUMU("Amumu"),
    ANIVIA("Anivia"),
    ANNIE("Annie"),
    APHELIOS("Aphelios"),
    ASHE("Ashe"),
    AURELION_SOL("Aurelion Sol"),
    AZIR("Azir"),

    BARD("Bard"),
    BEL_VETH("Bel'Veth"),
    BLITZCRANK("Blitzcrank"),
    BRAND("Brand"),
    BRAUM("Braum"),

    CAITLYN("Caitlyn"),
    CAMILLE("Camille"),
    CASSIOPEIA("Cassiopeia"),
    CHO_GATH("Cho'Gath"),
    CORKI("Corki"),

    DARIUS("Darius"),
    DIANA("Diana"),
    DR_MUNDO("Dr. Mundo"),
    DRAVEN("Draven"),

    EKKO("Ekko"),
    ELISE("Elise"),
    EVELYNN("Evelynn"),
    EZREAL("Ezreal"),

    FIDDLESTICKS("Fiddlesticks"),
    FIORA("Fiora"),
    FIZZ("Fizz"),

    GALIO("Galio"),
    GANGPLANK("Gangplank"),
    GAREN("Garen"),
    GNAR("Gnar"),
    GRAGAS("Gragas"),
    GRAVES("Graves"),
    GWEN("Gwen"),

    HECARIM("Hecarim"),
    HEIMERDIGER("Heimerdinger"),

    ILLAOI("Illaoi"),
    IRELIA("Irelia"),
    IVERN("Ivern"),

    JANNA("Janna"),
    JARVAN_IV("Jarvan IV"),
    JAX("Jax"),
    JAYCE("Jayce"),
    JHIN("Jhin"),
    JINX("Jinx"),

    KAI_SA("Kai'Sa"),
    KALISTA("Kalista"),
    KARMA("Karma"),
    KARTHUS("Karthus"),
    KASSADIN("Kassadin"),
    KATARINA("Katarina"),
    KAYLE("Kayle"),
    KAYN("Kayn"),
    KENNEN("Kennen"),
    KHA_ZIX("Kha'Zix"),
    KINDRED("Kindred"),
    KLED("Kled"),
    KOG_MAW("Kog'Maw"),

    LEBLANC("LeBlanc"),
    LEE_SIN("Lee Sin"),
    LEONA("Leona"),
    LILLIA("Lillia"),
    LISSANDRA("Lissandra"),
    LUCIAN("Lucian"),
    LULU("Lulu"),
    LUX("Lux"),

    MALPHITE("Malphite"),
    MALZAHAR("Malzahar"),
    MAOKAI("Maokai"),
    MASTER_YI("Master Yi"),
    MISS_FORTUNE("Miss Fortune"),
    MORDEKAISER("Mordekaiser"),
    MORGANA("Morgana"),

    NAMI("Nami"),
    NASUS("Nasus"),
    NAUTILUS("Nautilus"),
    NEEKO("Neeko"),
    NIDALEE("Nidalee"),
    NILAH("Nilah"),
    NOCTURNE("Nocturne"),
    NUNU("Nunu and Willump"),

    OLAF("Olaf"),
    ORIANNA("Orianna"),
    ORNN("Ornn"),

    PANTHEON("Pantheon"),
    POPPY("Poppy"),
    PYKE("Pyke"),

    QIYANA("Qiyana"),
    QUINN("Quinn"),

    RAKAN("Rakan"),
    RAMMUS("Rammus"),
    REK_SAI("Rek'Sai"),
    RELL("Rell"),
    RENATA_GLASC("Renata Glasc"),
    RENEKTON("Renekton"),
    RENGAR("Rengar"),
    RIVEN("Riven"),
    RUMBLE("Rumble"),
    RYZE("Ryze"),

    SAMIRA("Samira"),
    SEJUANI("Sejuani"),
    SENNA("Senna"),
    SERAPHINE("Seraphine"),
    SETT("Sett"),
    SHACO("Shaco"),
    SHEN("Shen"),
    SHYVANA("Shyvana"),
    SINGED("Singed"),
    SION("Sion"),
    SIVIR("Sivir"),
    SKARNER("Skarner"),
    SONA("Sona"),
    SORAKA("Soraka"),
    SWAIN("Swain"),
    SYLAS("Sylas"),
    SYNDRA("Syndra"),

    TAHM_KENCH("Tahm Kench"),
    TALIYAH("Taliyah"),
    TALON("Talon"),
    TARIC("Taric"),
    TEEMO("Teemo"),
    THRESH("Thresh"),
    TRISTANA("Tristana"),
    TRUNDLE("Trundle"),
    TRYNDAMERE("Tryndamere"),
    TWISTED_FATE("Twisted Fate"),
    TWITCH("Twitch"),

    UDYR("Udyr"),
    URGOT("Urgot"),

    VARUS("Varus"),
    VAYNE("Vayne"),
    VEIGAR("Veigar"),
    VEL_KOZ("Vel'Koz"),
    VEX("Vex"),
    VI("Vi"),
    VIEGO("Viego"),
    VIKTOR("Viktor"),
    VLADIMIR("Vladimir"),
    VOLIBEAR("Volibear"),

    WARWICK("Warwick"),
    WUKONG("Wukong"),

    XAYAH("Xayah"),
    XERATH("Xerath"),
    XIN_ZHAO("Xin Zhao"),

    YASUO("Yasuo"),
    YONE("Yone"),
    YORICK("Yorick"),
    YUUMI("Yuumi"),

    ZAC("Zac"),
    ZED("Zed"),
    ZERI("Zeri"),
    ZIGGS("Ziggs"),
    ZILEAN("Zilean"),
    ZOE("Zoe"),
    ZYRA("Zyra");

    private final String name;

    Champion(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
