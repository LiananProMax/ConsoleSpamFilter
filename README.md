# ConsoleSpamFilter

A plugin used to block garbage information output in the console. 

It can hide information you don’t want to see in the console.

`config.yml`:

```yml
no-permission-message: "&cYou lack the permission &7consolefilter.reload"
config-reloaded-message: "&cConsoleFilter &aConfig reloaded!"
keep-logs: 7
filter:
  - "Server thread/WARN"
  - "Ignoring advancement"

```