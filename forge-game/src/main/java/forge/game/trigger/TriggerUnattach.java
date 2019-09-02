/*
 * Forge: Play Magic: the Gathering.
 * Copyright (C) 2011  Forge Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package forge.game.trigger;

import forge.game.GameEntity;
import forge.game.card.Card;
import forge.game.spellability.SpellAbility;

/**
 * <p>
 * Trigger_Unattach class.
 * </p>
 * 
 * @author Forge
 */
public class TriggerUnattach extends Trigger {

    /**
     * <p>
     * Constructor for Trigger_Unequip.
     * </p>
     * 
     * @param params
     *            a {@link java.util.HashMap} object.
     * @param host
     *            a {@link forge.game.card.Card} object.
     * @param intrinsic
     *            the intrinsic
     */
    public TriggerUnattach(final java.util.Map<String, String> params, final Card host, final boolean intrinsic) {
        super(params, host, intrinsic);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean performTest(final java.util.Map<String, Object> runParams2) {
        final GameEntity object = (GameEntity) runParams2.get("Object");
        final Card attach = (Card) runParams2.get("Attach");

        if (hasParam("ValidObject")) {
            if (!object.isValid(getParam("ValidObject").split(","), getHostCard().getController(),
                    getHostCard(), null)) {
                return false;
            }
        }

        if (hasParam("ValidAttachment")) {
            return attach.isValid(getParam("ValidAttachment").split(","), getHostCard()
                    .getController(), getHostCard(), null);
        }

        return true;
    }

    /** {@inheritDoc} */
    @Override
    public final void setTriggeringObjects(final SpellAbility sa) {
        sa.setTriggeringObject("Object", getRunParams().get("Object"));
        sa.setTriggeringObject("Attach", getRunParams().get("Attach"));
    }

    @Override
    public String getImportantStackObjects(SpellAbility sa) {
        StringBuilder sb = new StringBuilder();
        sb.append("Object: ").append(sa.getTriggeringObject("Object")).append(", ");
        sb.append("Attachment: ").append(sa.getTriggeringObject("Attach"));
        return sb.toString();
    }

}
