package forge.game.replacement;

import forge.game.card.Card;
import forge.game.card.CounterType;
import forge.game.player.Player;
import forge.game.spellability.SpellAbility;

import java.util.Map;

/** 
 * TODO: Write javadoc for this type.
 *
 */
public class ReplaceAddCounter extends ReplacementEffect {

    /**
     * 
     * ReplaceProduceMana.
     * @param mapParams &emsp; HashMap<String, String>
     * @param host &emsp; Card
     */
    public ReplaceAddCounter(final Map<String, String> mapParams, final Card host, final boolean intrinsic) {
        super(mapParams, host, intrinsic);
    }

    /* (non-Javadoc)
     * @see forge.card.replacement.ReplacementEffect#canReplace(java.util.HashMap)
     */
    @Override
    public boolean canReplace(Map<String, Object> runParams) {
        if (!runParams.get("Event").equals("AddCounter") || ((int) runParams.get("CounterNum")) <= 0) {
            return false;
        }

        if (mapParams.containsKey("EffectOnly")) {
            final Boolean effectOnly = (Boolean) runParams.get("EffectOnly");
            if (!effectOnly) {
                return false;
            }
        }

        if (mapParams.containsKey("ValidCard")) {
            Object o = runParams.get("Affected");
            if (!(o instanceof Card)) {
                return false;
            }
            if (!matchesValid(o, this.getMapParams().get("ValidCard").split(","), this.getHostCard())) {
                return false;
            }
        } else if (mapParams.containsKey("ValidPlayer")) {
            Object o = runParams.get("Affected");
            if (!(o instanceof Player)) {
                return false;
            }
            if (!matchesValid(o, this.getMapParams().get("ValidPlayer").split(","), this.getHostCard())) {
                return false;
            }
        }

        if (mapParams.containsKey("ValidCounterType")) {
            String type = this.getMapParams().get("ValidCounterType");
            return CounterType.getType(type) == runParams.get("CounterType");
        }

        return true;
    }

    /* (non-Javadoc)
     * @see forge.card.replacement.ReplacementEffect#setReplacingObjects(java.util.HashMap, forge.card.spellability.SpellAbility)
     */
    @Override
    public void setReplacingObjects(Map<String, Object> runParams, SpellAbility sa) {
        sa.setReplacingObject("CounterNum", runParams.get("CounterNum"));
        sa.setReplacingObject("CounterType", ((CounterType) runParams.get("CounterType")).getName());
        Object o = runParams.get("Affected");
        if (o instanceof Card) {
            sa.setReplacingObject("Card", o);
        } else if (o instanceof Player) {
            sa.setReplacingObject("Player", o);
        }
    }

}
