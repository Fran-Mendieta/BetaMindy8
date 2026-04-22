package betamindy.content;

import arc.struct.*;
import betamindy.util.*;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.game.Objectives.*;
import mindustry.type.*;

import static mindustry.content.Items.*;
import static mindustry.content.Blocks.*;
import static mindustry.content.TechTree.*;
import static betamindy.content.MindyBlocks.*;
import static betamindy.content.MindyItems.*;

public class MindyTechTree{
    static TechTree.TechNode context = null;

    public static void load(){
        //Shar branch

        margeNode(ripple, () -> {
            node(anchor, () -> {
                node(bermuda);
            });
        });
        margeNode(tsunami, () -> {
            node(propaganda);
        });

        margeNode(vault, () -> {
            node(silo, () -> {
                node(warehouse);
            });
        });

        margeNode(microProcessor, () -> {
            node(heatSink, () -> {
                node(heatFan);
                node(heatSinkLarge);
            });
        });

        margeNode(incinerator, () -> {
            node(campfire);
        });

        margeNode(logicDisplay, () -> {
            node(noteBlock);
        });
    }

    //TODO: replace this with the standard TechTree API, it's public now -Anuke

    private static void margeNode(UnlockableContent parent, Runnable children){
        context = TechTree.all.find(t -> t.content == parent);
        children.run();
    }

    private static void node(UnlockableContent content, ItemStack[] requirements, Seq<Objective> objectives, Runnable children){
        TechNode node = new TechNode(context, content, requirements);
        if(objectives != null) node.objectives = objectives;

        TechNode prev = context;
        context = node;
        children.run();
        context = prev;
    }

    private static void node(UnlockableContent content, ItemStack[] requirements, Runnable children){
        node(content, requirements, null, children);
    }

    private static void node(UnlockableContent content, Seq<Objective> objectives, Runnable children){
        node(content, content.researchRequirements(), objectives, children);
    }

    private static void node(UnlockableContent content, Runnable children){
        node(content, content.researchRequirements(), children);
    }

    private static void node(UnlockableContent block){
        node(block, () -> {});
    }

    private static void nodeProduce(UnlockableContent content, Seq<Objective> objectives, Runnable children){
        node(content, content.researchRequirements(), objectives.add(new Produce(content)), children);
    }

    private static void nodeProduce(UnlockableContent content, Runnable children){
        nodeProduce(content, Seq.with(), children);
    }

    private static void nodeProduce(UnlockableContent content){
        nodeProduce(content, Seq.with(), () -> {});
    }

    private static void nodePortal(UnlockableContent content, int level, Runnable children){
        node(content, content.researchRequirements(), Seq.with(new ObjectivesM.PortalLevel(level)), children);
    }

    private static void nodePortal(UnlockableContent content, int level){
        nodePortal(content, level, () -> {});
    }
}
