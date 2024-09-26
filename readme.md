# Proximity
A simple Proximity Text chat mod/API

[![curseforge](https://img.shields.io/badge/-CurseForge-gray?style=for-the-badge&logo=curseforge&labelColor=orange)](https://www.curseforge.com/minecraft/mc-mods/proximity) 
[![modrinth](https://img.shields.io/badge/-modrinth-gray?style=for-the-badge&labelColor=green&labelWidth=15&logo=appveyor&logoColor=white)](https://modrinth.com/mod/proximity)
[![release](https://img.shields.io/badge/Source-grey?style=for-the-badge&logo=github)](https://github.com/chyzman/proximity)

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
