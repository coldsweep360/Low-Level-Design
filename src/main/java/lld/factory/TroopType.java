package lld.factory;

/**
 * Identifies the concrete troop variant requested by a client.
 *
 * The client supplies this stable domain value to {@link TroopsFactory}
 * instead of supplying a class name or a string such as "archer". This gives
 * us compile-time validation, discoverable options in an IDE, and a single
 * place where the mapping from type to implementation is maintained.
 */
public enum TroopType {
    ARCHER,
    BARBARIAN,
    WIZARD
}
