
# 

* goto () -> [honey](https://github.com/rchomczyk/honey) a better. more flexible, and more efficient api with a fluent flow ^


# Usage:

```java
/* for titles::audience */
dispatcher.createTitle()
    .recipient(event.getPlayer())
    .title(it -> it.template("Hello!"))
    .subtitle(it -> it.template("It is a pleasure to see you there {{player.getName}}")
    .variable("player", event.getPlayer()))
    .times(2, 4, 2)
    .dispatch();

/* for chat::audience */
dispatcher.createChat()
    .recipient(Bukkit.getServer())
    .template("{{player.getName}} has joined the server!")
    .variable("player", event.getPlayer())
    .dispatch();

/* for actionbar::audience */
dispatcher.createActionBar()
    .recipient(event.getPlayer())
    .template("Honey is great, isn't it?")
    .dispatch();
```
