# Spring Cloud Dataflow IRC Source app

Connects to an IRC server and listens to messages on the specified channels.

### Example stream definition

`ircsource --irc-server=irc.rizon.net --channels=#aturoktest,#aturok2test --irc-nickname=botybot | log`