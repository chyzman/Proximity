# Proximity
A simple Proximity Text chat mod/API

[![Badge showing the amount of downloads on modrinth](https://img.shields.io/badge/dynamic/json?color=2d2d2d&colorA=5da545&label=&suffix=%20downloads%20&query=downloads&url=https://api.modrinth.com/v2/project/GyF8sewW&style=flat-square&logo=modrinth&logoColor=2d2d2d)](https://modrinth.com/mod/proximity)
[![Badge showing the amount of downloads on curseforge](https://cf.way2muchnoise.eu/full_1107098_downloads.svg?badge_style=flat)](https://www.curseforge.com/minecraft/mc-mods/proximity)
[![Badge linking to issues on github](https://img.shields.io/badge/dynamic/json?query=value&url=https%3A%2F%2Fimg.shields.io%2Fgithub%2Fissues-raw%2Fchyzman%2Fproximity.json&label=&logo=github&color=2d2d2d&style=flat-square&labelColor=6e5494&logoColor=2d2d2d&suffix=%20issues)](https://github.com/chyzman/proximity/issues)

Proximity modifies the vanilla chat system in order to make it proximity based. This includes:
1. Configurable chat distance
2. Configurable chat command distance (/me /say)
3. Configurable Mumble command for talking to people very close by (/mumble or /m)
4. Shout command which everyone can hear (/shout or /s)

Everything is configurable via gamerules

Proximity also offers a simple api that other mods can use to integrate with proximity's proximity chat system
1. Mods can force certain players to be able to hear/not hear other players under certain circumstances
2. Mods can modify the position that proximity will use for distance calculations so that, for Example, a mod that adds some sort of machine in a box can make the location the outside of the box.

Proximity also adds 2 attributes:
1. Hearing distance which increases the distance (in blocks) a player will be able to hear messages from
2. Speaking distance which increases the distance (in blocks) a player's messages will be heard from
