package betamindy.content;

import arc.audio.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.struct.*;
import arc.util.*;
import betamindy.entities.bullet.*;
import betamindy.graphics.*;
import betamindy.type.*;
import betamindy.type.shop.*;
import betamindy.world.blocks.campaign.*;
import betamindy.world.blocks.defense.*;
import betamindy.world.blocks.defense.turrets.*;
import betamindy.world.blocks.defense.turrets.pattern.*;
import betamindy.world.blocks.distribution.*;
import betamindy.world.blocks.environment.*;
import betamindy.world.blocks.logic.*;
import betamindy.world.blocks.payloads.*;
import betamindy.world.blocks.power.*;
import betamindy.world.blocks.production.*;
import betamindy.world.blocks.production.payduction.*;
import betamindy.world.blocks.storage.*;
import betamindy.world.blocks.units.*;
import betamindy.world.draw.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.entities.effect.*;
import mindustry.entities.pattern.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.logic.*;
import mindustry.world.blocks.payloads.*;
import mindustry.world.blocks.payloads.PayloadDeconstructor;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

import static betamindy.BetaMindy.*;
import static betamindy.content.ShopItems.*;
import static betamindy.util.BlockLib.*;
import static mindustry.type.ItemStack.*;

@SuppressWarnings("deprecation")
public class MindyBlocks{
        public static Block 

    //effect
    silo, warehouse, terraformer1, terraformer2, terraformer3, terraformer4,box,
    //walls
    leadWall, leadWallLarge, metaglassWall, metaglassWallLarge, siliconWall, siliconWallLarge, graphiteWall, graphiteWallLarge, coalWall, coalWallLarge, pyraWall, pyraWallLarge, blastWall, blastWallLarge, teamWall,
    
    //units
    boostPad, bumper, portalPad,
    //logic
    linkPin, heatSink, heatFan, heatSinkLarge, noteBlock, starNoteBlock,
    //turrets
    anchor, bermuda, propaganda, spear, justice, sting, ray, magicTurret, mortgage,
            //campaign - shar
            giant, dwarf, spectrum,
    //power
    pressurePad, pressurePadLarge, isotopeReactor,
    //crafting
    scalarFurnace,
           
    //catalysts (pushreact & spinreact & boost)
    campfire;

    public static void load() {
        //turrets

        anchor = new ItemTurret("anchor"){{
            requirements(Category.turret, with(Items.lead, 80, Items.graphite, 65, Items.titanium, 50));

            reload = 80f;
            shake = 3f;
            range = 160f;
            recoil = 4f;
            size = 2;
            targetAir = false;

            health = 220 * size * size;
            shootSound = Sounds.shootFuse;
            placeableLiquid = true;

            ammo(
                    Items.titanium, new NavalBulletType(2.5f, 130f){{
                        drag = 0.0095f;
                        lifetime = 100f;
                        ammoMultiplier = 4f;
                        reloadMultiplier = 1.3f;
                    }},
                    Items.thorium, new NavalBulletType(2.5f, 190f){{
                        drag = 0.0095f;
                        lifetime = 100f;
                        ammoMultiplier = 5f;
                        width = 8.5f;
                        toColor = Pal.thoriumPink;
                        shootEffect = smokeEffect = Fx.thoriumShoot;
                        despawnEffect = MindyFx.thoriumDespawn;
                    }}
            );
        }};

        bermuda = new ItemTurret("bermuda"){{
            requirements(Category.turret, with(Items.lead, 220, Items.graphite, 160, Items.thorium, 105, Items.plastanium, 85));

            reload = 40f;
            shake = 3f;
            range = 230f;
            recoil = 5f;
            size = 3;
            targetAir = false;

            health = 240 * size * size;
            shootSound = Sounds.shotgun;
            placeableLiquid = true;

            ammo(
                    Items.titanium, new NavalBulletType(3.5f, 240f){{
                        length = 55f;
                        width = 8f;
                        lifetime = 120f;
                        ammoMultiplier = 4f;
                        reloadMultiplier = 1.3f;
                    }},
                    Items.thorium, new NavalBulletType(3.5f, 424f){{
                        length = 55f;
                        lifetime = 120f;
                        ammoMultiplier = 5f;
                        width = 10.5f;
                        toColor = Pal.thoriumPink;
                        shootEffect = smokeEffect = Fx.thoriumShoot;
                        despawnEffect = MindyFx.thoriumDespawn;
                    }}
            );
        }};

        propaganda = new ItemTurret("propaganda"){{
            requirements(Category.turret, with(Items.copper, 500, Items.graphite, 360, Items.metaglass, 65, Items.phaseFabric, 65));

            reload = 160f;
            range = 240f;
            recoil = 2f;
            size = 3;
            shoot.shots = 3;
            shoot.shotDelay = 10f;

            health = 140 * size * size;
            shootSound = Sounds.plasmadrop;
            heatColor = Pal.lancerLaser;
            shootY = 2f;
            coolant = consume(consumeCoolant(1f));
            coolantMultiplier = 0.4f;

            consumePower(18f);

            ammo(
                    Items.metaglass, new SoundwaveBulletType(4.5f, 60f, MindyStatusEffects.dissonance){{
                        fromColor = toColor = hitColor = Color.white;
                        lifetime = 60f;
                        ammoMultiplier = 4f;
                        reloadMultiplier = 2.5f;
                    }},
                    MindyItems.bittrium, new SoundwaveBulletType(4.5f, 100f, MindyStatusEffects.radiation){{
                        fromColor = Items.phaseFabric.color;
                        toColor = hitColor = Pal.sapBullet;
                        lifetime = 90f;
                        ammoMultiplier = 3f;
                    }},
                    Items.surgeAlloy, new SoundwaveBulletType(4.5f, 5f, MindyStatusEffects.reverseBiased){{
                        fromColor = hitColor = Pal.surge;
                        toColor = Color.orange;
                        lifetime = 110f;
                        ammoMultiplier = 5f;
                    }}
            );
        }};

        spectrum = new ItemTurret("spectrum"){{
            requirements(Category.turret, with(Items.copper, 30, MindyItems.bittrium, 35, Items.silicon, 8));

            range = 130f; //must be set before ammo()
            ammo(
                    Items.copper, new ItemTrailBulletType(6, 4.5f, range, FireColor.fromMap.get(Items.copper)),
                    Items.titanium, new ItemTrailBulletType(20, 7f, range, Color.cyan){{
                        pierceCap = 4;
                        reloadMultiplier = 1.2f;
                    }}
            );
            health = 500;
            size = 1;
            reload = 15f;
            inaccuracy = 1.5f;
            shootCone = 30f;
            //shootSound = MindySounds.pewRetro; //this breaks the audio bus for some reason
        }};

        ray = new RayTurret("ray"){{
            requirements(Category.turret, with(Items.copper, 320, Items.silicon, 255, Items.titanium, 150, MindyItems.bittrium, 200));
            size = 3;
            range = 234f;
        }};

        scalarFurnace = new NuclearCrafter("scalar-furnace"){{
            requirements(Category.crafting, with(Items.copper, 400, Items.silicon, 100, Items.graphite, 100, Items.thorium, 450, Items.surgeAlloy, 150));
            ambientSound = Sounds.hum;
            ambientSoundVolume = 0.24f;
            explodeEffect = MindyFx.scalarReactorExplosion;
            size = 4;
            health = 1200;
            itemDuration = 120f;
            itemCapacity = 30;
            powerProduction = 17f;
            consumeItems(with(Items.thorium, 1, Items.graphite, 1, Items.metaglass, 1, Items.phaseFabric, 1));
            outputItem = new ItemStack(MindyItems.bittrium, 4);
            heating = 0.01f;
            consumeLiquid(Liquids.cryofluid, heating * 2f / coolantPower).update(false);
            coolantPower = 0.1f;

            explosionRadius = 24;
            explosionDamage = 1850;
        }};

        isotopeReactor = new IsotopeReactor("isotope-reactor"){{
            requirements(Category.power, with(Items.copper, 30, MindyItems.bittrium, 15));
            size = 1;
            powerProduction = 1f;
            ores.put(Blocks.oreThorium, 1f);
        }};

        silo = new StorageBlock("silo"){
            public TextureRegion iconRegion;

            @Override
            public void load(){
                super.load();
                iconRegion = Drawm.getTeamRegion(this);
            }

            @Override
            public void createIcons(MultiPacker packer){
                Drawm.generateTeamRegion(packer, this);
                super.createIcons(packer);
            }

            @Override
            public TextureRegion[] icons(){
                return new TextureRegion[]{region, iconRegion};
            }

            {
                size = 4;
                itemCapacity = 2500;
                flags = EnumSet.of(BlockFlag.storage);
                requirements(Category.effect, with(Items.titanium, 400, Items.thorium, 250, Items.plastanium, 200, Items.phaseFabric, 100));
            }
        };

        warehouse = new StorageBlock("warehouse"){
            public TextureRegion iconRegion;

            @Override
            public void load(){
                super.load();
                iconRegion = Drawm.getTeamRegion(this);
            }

            @Override
            public void createIcons(MultiPacker packer){
                Drawm.generateTeamRegion(packer, this);
                super.createIcons(packer);
            }

            @Override
            public TextureRegion[] icons(){
                return new TextureRegion[]{region, iconRegion};
            }

            {
                size = 5;
                itemCapacity = 7000;
                flags = EnumSet.of(BlockFlag.storage);
                requirements(Category.effect, with(Items.titanium, 600, Items.thorium, 500, Items.plastanium, 450, Items.phaseFabric, 150, Items.surgeAlloy, 100));
            }
        };

        leadWall = new Wall("lead-wall"){{
            health = 360;
            requirements(Category.defense, with(Items.lead, 6));
        }};

        leadWallLarge = new Wall("lead-wall-large"){{
            health = 1440;
            size = 2;
            requirements(Category.defense, with(Items.lead, 24));
        }};

        metaglassWall = new ShardWall("metaglass-wall"){{
            shard = MindyBullets.glassPiece;
            amount = 5;
            inaccuracy = 36f;
            health = 380;
            requirements(Category.defense, with(Items.graphite, 3, Items.metaglass, 5));
        }};

        metaglassWallLarge = new ShardWall("metaglass-wall-large"){{
            shard = MindyBullets.glassPieceBig;
            amount = 8;
            inaccuracy = 24f;
            health = 1520;
            size = 2;
            distRand = 6.5f;
            requirements(Category.defense, with(Items.graphite, 12, Items.metaglass, 20));
        }};

        siliconWall = new Wall("silicon-wall"){{
            health = 370;
            insulated = true;
            requirements(Category.defense, with(Items.copper, 2, Items.silicon, 5));
        }};

        siliconWallLarge = new Wall("silicon-wall-large"){{
            health = 1480;
            insulated = true;
            size = 2;
            requirements(Category.defense, with(Items.copper, 8, Items.silicon, 20));
        }};

        graphiteWall = new AbsorbWall("graphite-wall"){{
            health = 420;
            requirements(Category.defense, with(Items.graphite, 6, Items.titanium, 4));
        }};

        graphiteWallLarge = new AbsorbWall("graphite-wall-large"){{
            health = 420 * 4;
            size = 2;
            requirements(Category.defense, with(Items.graphite, 24, Items.titanium, 16));
        }};

        coalWall = new IgniteWall("coal-wall"){{
            health = 240;
            baseExplosiveness = 3f;
            variants = 2;
            requirements(Category.defense, with(Items.coal, 6));
        }};

        coalWallLarge = new IgniteWall("coal-wall-large"){{
            health = 960;
            baseExplosiveness = 12f;
            size = 2;
            variants = 2;
            requirements(Category.defense, with(Items.coal, 24));
        }};

        pyraWall = new ShardWall("pyra-wall"){{
            shard = Bullets.fireball;
            amount = 6;
            health = 490;
            requirements(Category.defense, with(Items.pyratite, 6));
        }};

        pyraWallLarge = new ShardWall("pyra-wall-large"){{
            shard = Bullets.fireball;
            amount = 24;
            health = 1960;
            size = 2;
            distRand = 4.5f;
            requirements(Category.defense, with(Items.pyratite, 24));
        }};

        blastWall = new Wall("blast-wall"){{
            health = 510;
            baseExplosiveness = 12.5f;
            requirements(Category.defense, with(Items.coal, 2, Items.blastCompound, 8));
        }};

        blastWallLarge = new Wall("blast-wall-large"){{
            health = 2040;
            baseExplosiveness = 50f;
            size = 2;
            requirements(Category.defense, with(Items.coal, 8, Items.blastCompound, 32));
        }};

        teamWall = new TeamWall("team-wall"){{
            health = 360;
            requirements(Category.defense, with(Items.titanium, 6, Items.graphite, 6, Items.silicon, 12));
        }};

        boostPad = new BoostPad("boostpad"){{
            size = 2;
            requirements(Category.units, with(Items.lead, 24, Items.silicon, 10, Items.phaseFabric, 30));
            lightColor = Color.orange;
            lightRadius = 50f;
        }};

        portalPad = new TeleportPortal("teleport-portal"){{
            requirements(Category.units, with( Items.scrap, 320, Items.copper, 250, Items.phaseFabric, 75, MindyItems.bittrium, 80));
            size = 3;
            animateNear = false;
            heatLerp = 0.02f;
            lightColor = Color.white;
            inSound = MindySounds.easterEgg1;
            outSound = MindySounds.easterEgg2;
            teleportIn = MindyFx.unitInPortal;
            teleportOut = MindyFx.portalWaveSmall;
            teleportUnit = MindyFx.unitOutPortal;
            hasShadow = false;
            consumePower(25f);
        }};

        linkPin = new LinkPinner("linkpin"){{
            requirements(Category.logic, with( Items.graphite, 30, Items.silicon, 15, Items.metaglass, 30));
        }};

        heatSink = new ProcessorCooler("heatsink"){{
            size = 2;
            requirements(Category.logic, with( Items.titanium, 70, Items.silicon, 25, Items.plastanium, 65));
        }};

        heatFan = new ProcessorFan("coolerfan"){{
            size = 3;
            boost = 3;
            maxProcessors = 5;
            consumePower(4f);
            requirements(Category.logic, with( Items.titanium, 90, Items.silicon, 50, Items.plastanium, 50, Items.phaseFabric, 25));
        }};

        heatSinkLarge = new ProcessorCooler("waterblock"){{
            size = 3;
            boost = 2;
            maxProcessors = 6;
            liquidCapacity = 640;
            acceptCoolant = true;
            //consumeLiquid(Liquids.water, 3f);
            requirements(Category.logic, with( Items.titanium, 110, Items.silicon, 50, Items.metaglass, 40, Items.plastanium, 30, Items.surgeAlloy, 15));
        }};

        noteBlock = new NotePlayer("note-block"){{
            consumePower(0.5f);
            requirements(Category.logic, with(Items.silicon, 5, Items.graphite, 10));
        }};

        starNoteBlock = new NotePlayer("star-note-block"){{
            global = true;
            consumePower(0.8f);
            requirements(Category.logic, with(Items.silicon, 5, Items.graphite, 10, MindyItems.bittrium, 1));
        }};

        bumper = new Bumper("bumper"){{
            health = 300;
            requirements(Category.units, with(Items.lead, 30, Items.graphite, 15, Items.metaglass, 15));
            size = 2;
            chanceDeflect = 10f;
        }};

        campfire = new Campfire("campfire"){{
            size = 2;
            health = 400;
            itemCapacity = 30;
            buildCostMultiplier = 3f;

            fireEffect = MindyFx.bigFire;
            fireDustEffect = MindyFx.bigFireDust;
            smokeChance = 0.15f;
            statusDuration = 2400f;
            statusReload = 360f;
            requirements(Category.effect, with(Items.copper, 48, Items.titanium, 20, Items.sand, 10));
        }};
    }
}
