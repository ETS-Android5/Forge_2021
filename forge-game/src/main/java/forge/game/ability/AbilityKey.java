package forge.game.ability;

import java.util.EnumMap;
import java.util.Map;

import forge.game.card.Card;

/**
 * Keys for Ability parameter maps.
 */
public enum AbilityKey {
    AbilityMana("AbilityMana"),
    Activator("Activator"),
    Affected("Affected"),
    AllVotes("AllVotes"),
    Amount("Amount"),
    Attach("Attach"),
    AttachSource("AttachSource"),
    AttachTarget("AttachTarget"),
    Attacked("Attacked"),
    Attacker("Attacker"),
    Attackers("Attackers"),
    AttackingPlayer("AttackingPlayer"),
    AttackedTarget("AttackedTarget"),
    Blocker("Blocker"),
    Blockers("Blockers"),
    CastSA("CastSA"),
    CastSACMC("CastSACMC"),
    Card("Card"),
    Cards("Cards"),
    CardLKI("CardLKI"),
    Cause("Cause"),
    Causer("Causer"),
    Championed("Championed"),
    CopySA("CopySA"),
    Cost("Cost"),
    CostStack("CostStack"),
    CounterAmount("CounterAmount"),
    CounteredSA("CounteredSA"),
    CounterType("CounterType"),
    Crew("Crew"),
    CumulativeUpkeepPaid("CumulativeUpkeepPaid"),
    CurrentCastSpells("CurrentCastSpells"),
    CurrentStormCount("CurrentStormCount"),
    DamageAmount("DamageAmount"),
    DamageSource("DamageSource"),
    DamageSources("DamageSources"),
    DamageTarget("DamageTarget"),
    DamageTargets("DamageTargets"),
    Defender("Defender"),
    Defenders("Defenders"),
    DefendingPlayer("DefendingPlayer"),
    Destination("Destination"),
    Devoured("Devoured"),
    EchoPaid("EchoPaid"),
    Exploited("Exploited"),
    Explorer("Explorer"),
    Event("Event"),
    Fighter("Fighter"),
    FirstTime("FirstTime"),
    Fizzle("Fizzle"),
    IsCombatDamage("IsCombatDamage"),
    IndividualCostPaymentInstance("IndividualCostPaymentInstance"),
    IsMadness("IsMadness"),
    LifeAmount("LifeAmount"),
    MonstrosityAmount("MonstrosityAmount"),
    NewCounterAmount("NewCounterAmount"),
    Num("Num"), // TODO confirm that this and NumThisTurn can be merged
    NumBlockers("NumBlockers"),
    NumThisTurn("NumThisTurn"),
    Number("Number"),
    Object("Object"),
    Objects("Objects"),
    OtherAttackers("OtherAttackers"),
    OtherVoters("OtherVoters"),
    Origin("Origin"),
    OriginalController("OriginalController"),
    OriginalDefender("OriginalDefender"),
    PayingMana("PayingMana"),
    Phase("Phase"),
    Player("Player"),
    Produced("Produced"),
    Result("Result"),
    Scheme("Scheme"),
    Source("Source"),
    Sources("Sources"),
    SourceSA("SourceSA"),
    SpellAbility("SpellAbility"),
    SpellAbilityStackInstance("SpellAbilityStackInstance"),
    SpellAbilityTargetingCards("SpellAbilityTargetingCards"),
    StackInstance("StackInstance"),
    StackSa("StackSa"),
    StackSi("StackSi"),
    Target("Target"),
    Targets("Targets"),
    Transformer("Transformer"),
    Vehicle("Vehicle"),
    Won("Won");


    private String key;

    AbilityKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }

    /**
     * @param s A string that would be output from toString
     * @return the corresponding key if there is one or null otherwise
     */
    public static AbilityKey fromString(String s) {
        for (AbilityKey k : values()) {
            if (k.toString().equalsIgnoreCase(s)) {
                return k;
            }
        }
        return null;

    }

    public static <V> EnumMap<AbilityKey, V> newMap() {
        return new EnumMap<>(AbilityKey.class);
    }

    public static <V> EnumMap<AbilityKey, V> newMap(Map<AbilityKey, V> map) {
        // The EnumMap constructor throws IllegalArgumentException if the map is empty.
        if (map.isEmpty()) {
            return newMap();
        }
        return new EnumMap<>(map);
    }

    public static Map<AbilityKey, Object> mapFromCard(Card card) {
        final Map<AbilityKey, Object> runParams = newMap();

        runParams.put(Card, card);
        return runParams;
    }
}