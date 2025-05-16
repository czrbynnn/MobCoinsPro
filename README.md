**_MobCoinCore_**
Version: 1.0.0
Author: czrbyn

**Overview**
MobCoin Core is a lightweight, efficient Minecraft plugin that manages and tracks MobCoins, a custom currency earned by players for killing mobs. This plugin only handles the core MobCoin economy—granting and storing MobCoins for players—and provides a solid API for other plugins or addons to extend its functionality.

It also integrates seamlessly with PlaceholderAPI (PAPI), allowing you to display MobCoin balances easily via placeholders such as %mobcoins_balance%.

**Features**
Simple and stable MobCoin balance system

Persistent MobCoin storage per player

Built-in API for other developers to hook into and expand upon

PlaceholderAPI support for easy integration with chat, scoreboards, and GUIs

Minimalist core focused solely on tracking MobCoins — no additional gameplay features included by default

**Important Notes**
This plugin does **NOT** include additional features like shops or rewards out of the box.

To get access to extended features, plugins, or updates beyond the core, please join our Discord community (link below) where future enhancements and support are available.

The core API allows developers to build custom extensions that interact with MobCoins however they want.

PlaceholderAPI Support
Use %mobcoins_balance% to get a player’s current MobCoin balance in any PAPI-supported plugin, such as chat plugins, scoreboard plugins, or GUI frameworks.

Installation
Place the MobCoinCore.jar file into your server’s plugins folder.

Restart or reload your server.

Configure the plugin using the configuration files generated in plugins/MobCoinCore.

Use the provided API or commands to interact with MobCoins.

API Usage (For Developers)
The plugin exposes an API to:

Get or set a player’s MobCoin balance

Listen for MobCoin-related events

Integrate custom features like shops, leaderboards, or rewards

**access the api like this:**

_Add to pom.xml:_

    <repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

_Then, add the dependency:_

    <dependency>
	    <groupId>com.github.czrbynnn</groupId>
	    <artifactId>MobCoinsPro</artifactId>
	    <version>1.0.0</version>
	</dependency>

Support & Community
If you want to report bugs, request features, or get the latest updates and addons, please join our official Discord:

**_https://discord.gg/wQRZTvz7Qd_**

Credits
Created and maintained by czrbyn (5lagg ingame)
