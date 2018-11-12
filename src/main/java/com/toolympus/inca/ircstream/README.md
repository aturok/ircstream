# Spring Cloud Dataflow IRC Source app

Connects to an IRC server and listens to messages on the specified channels.

### Example stream definition

`ircsource --irc-server=chat.freenode.net --channels=#aturoktest,#python --irc-nickname=incabot --irc-password=BotyBot | log`