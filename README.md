meetingslog
===========

Meetings log project

Module dependencies and visibility:

root
|
---core
   |
   --- services
      |
      ---facades
      |
      --- web

web can access facades and services
facades can access services
services can access core
