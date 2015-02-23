# Done with Diabetes

## Overview


People who have Type 1 diabetes are born with it. Usually diagnosed at a young age, Type 1 diabetes is a serious issue that requires a lot of oversight (oversight many young patients aren’t willing to give it). An individual with Type 1 diabetes has a pancreas that is no longer producing insulin and therefore the individual must undergo daily injections of insulin. They must also self-monitor their blood glucose levels, their nutrition and exercise. As a result, it can often feel like their disease defines their lives. This app is going to help them develop the habits that are necessary to develop as an individual with Type 1 diabetes as well as remind them that their disease doesn’t define them. 
For more information.


## Minimum Viable Product


A 2D game that has a built in injection and blood check reminder with a calorie count. 


## General Features

* reminding of taking insulin
   * as part of setting up app have user input when they are expected to take injection
* calorie count
   * Provide basic calorie count examples (or dump the database for them)
   * Calorie count calculator tool
* blood glucose checking
   * also have user input when they should be taking injections as part of setting up app
* reward/incentivise treatment


   * In-game “friend” (pet/partner) to help
   * General game points, exp, items, gifts, surprises
* reminder to exercise
* teaching them about diabetes, in general
   * loading screen = random facts about it 
      * maybe also have it have inspiration/motivational quotes?
      * maybe famous individuals with the disease?
* parent kid interaction
   * possible end purpose: Allow parents to directly support their kids while getting them to interact and understand one another
   * companion app/alternate access for parents to verify kids’ data
   * rewards for successfully taking insulin/etc
   * mini-game to compete against each other
* actual game
   * rpg mechanics?
      * gold/items/levels
      * quests/challenges (include quests involving real live)
         * example “injected every day for 50 days” quest for 50xp
      * actual gameplay
         * either some kind of “fake” combat/movement system where it’s just tapping and random rolls based on stats
         * or some kind of real interactive gameplay/minigame
            * puzzle game
            * Dots and Boxes
            * Snake
            * twin stick (non-violent) shooter
            * Basic racing game
            * cooking game? (Like poffin thing from pokemon)
                        
* online community
   * no chat between the kids (?)
      * can use predefined messages, like many kid-friendly games
* help each other - if one is suffering then someone else can reward 
     
## True Reach/After-Having-Everything-Done Features

* including some way to have kids without diabetes to be involved
   * to create an inclusive, welcoming atmosphere (and help teach others who don’t have Diabetes what it really is)
* web game

## Architecture

### Backend
    
* Data to Track
* Calories
  * Calories per meal, per day
  * Access to calorie database (as examples)
  * Injections (times)
* Blood samples
  * Exercise
     * reminder (times and/or what)
  * Individual information (i.e. rewards won, etc)
* How to Track
  * User input
  * Parental authorization
* Reminders/notifications for both platforms
* Server for kid/parent pairing and data transmission/storage
  * store calorie data
            
### Languages
    
* C#
    
    
### Frameworks

* Unity3D Engine
* Database
    
### APIs/Resources

* USDA National Nutrient Database
* Analyze how Kids Health handles teaching Diabetes (as well as other sources)
* Cloud services


## Creative Assets

* Environment
   * Characters
* Game icons + logos (+ tshirts + PR material, etc)


## Hardware

* iOS and Android
* Server for communication and other interactions
* Reach goal: web

## Tentative Timeline
* 2/23: Spec
* 3/23: MVP
* 5/23: Beta
* ...


## Remaining Questions / Tasks

* What do these kids currently face that is especially difficult? What’s relatively easy for them?
* How are kids taught currently about all of these important things? (What’s been proven successful and what hasn’t?)
* How to attract a community of people that will benefit?