```mermaid
graph LR
    Start --> |North| CentralSpire
    Start --> |West| Left_Path
    Start --> |East| StrightPath[Straight Path]
    
    Left_Path --> |West| ExploreCave[Explore Cave]
    Left_Path --> |East| BoatRide[Boat Ride]
    Left_Path --> |East| Start
    
    BoatRide --> |West| HiddenTemple[Hidden Temple]
    BoatRide --> |North| FaintTunnel[Faint Tunnel]
    
    FaintTunnel --> |West| MysteriousCavern[Mysterious Cavern]
    MysteriousCavern --> |North| CrystalHollow[Crystal Hollow]
    MysteriousCavern --> |South| DarkCavern[Dark Cavern]
    
    HiddenTemple --> |East| HiddenPassageway[Hidden Passageway]
    HiddenPassageway --> |North| DarkCavern
    
    DarkCavern --> |East| IronDoor[Iron Door]
    DarkCavern --> |West| GlowingPassage[Glowing Passage]
    
    IronDoor --> |East| DarkRoom[Dark Room]
    DarkRoom --> |South| FrozenAbyss[Frozen Abyss]
    FrozenAbyss --> |West| FrozenShrine[Frozen Shrine]
    FrozenAbyss --> |East| IcyChamber[Icy Chamber]
    
    GlowingPassage --> |North| UnlockedPassageway[Unlocked Passageway]
    UnlockedPassageway --> |East| SlopingDarkness[Sloping Darkness]
    UnlockedPassageway --> |East| FlowingWaterRoom[Flowing Water Room]
    
    FlowingWaterRoom --> |West| MysticCavern[Mystic Cavern]
    FlowingWaterRoom --> |North| CrystalPool[Crystal Pool]
    
    SlopingDarkness --> |East| LuminousChamber[Luminous Chamber]
    SlopingDarkness --> |North| PumblingChamber[Pumbling Chamber]
    SlopingDarkness --> |West| ShadowedAlcove[Shadowed Alcove]
    
    PumblingChamber --> |North| EchoingHall[Echoing Hall]
    PumblingChamber --> |South| CollapsedTunnel[Collapsed Tunnel]
    
    CentralSpire --> |East| GlassGardens[Glass Gardens]
    CentralSpire --> |West| Grandfell
    CentralSpire --> |South| OvergrownAtrium[Overgrown Atrium]
    
    Grandfell --> |North| SkyBridgePins[Skybridge Pins]
    Grandfell --> |West| IndustrialSector[Industrial Sector]
    
    OvergrownAtrium --> |East| CrystalFountain[Crystal Fountain]
    OvergrownAtrium --> |South| MarketPlaza[Market Plaza]
    OvergrownAtrium --> |North| ObsidianNexus[Obsidian Nexus]
    
    ObsidianNexus --> |West| HiddenBiotechLab[Hidden Biotech Lab]
