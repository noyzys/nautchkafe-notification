package dev.nautchkafe.fmap.notification.extension

import io.vavr.control.Option
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*

typealias UserId = UUID
typealias FindPlayer = Option<Player>

fun UUID.findPlayer(): FindPlayer =
    Option.of(Bukkit.getPlayer(this))

/**
 * Finds a Player instance associated with the given name.
 *
 * This extension function returns an Option containing the Player if found, otherwise returns an empty Option.
 *
 * @param name the name of the player to find
 * @return an Option<Player> representing the found player or empty if not found
 */
fun String.findPlayer(): FindPlayer =
    Option.of(Bukkit.getPlayer(this))

/**
 * Finds a Player instance associated with the given UUID.
 *
 * This extension function returns an Option containing the Player if found, otherwise returns an empty Option.
 *
 * @param uniqueId the UUID of the player to find
 * @return an Option<Player> representing the found player or empty if not found
 */
fun findPlayer(uniqueId: UserId): FindPlayer =
    Option.of(Bukkit.getPlayer(uniqueId))