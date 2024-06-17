# Project theme

My project theme is "Biathlon". The database will contain biathlon data, such as competition results, biathletes, stadiums, and biathlete stats. It will be useful for making statistics. I have been following this sport for a long time, so I have enough knowledge to do this project properly.

Our environment consists of **Persons**. Each **Person** is identified by their email and has a *name*, *surname*, and *sex*. Each **Person** can have only one **Login** or not have any. A **Login** is identified by a username and has a *password*. Every **Login** is associated with just one **Person**.

**Staff** is a **Person** who works at a *position* and has an employeeID. **Staff** works in a **Stadium**.

**Viewer** is a **Person** who has a viewerID and has bought at least one **Ticket**.

**Biathlete** is a **Person**. A **Biathlete** is identified by a biathleteID and can have *teammates* from other **Biathletes**. A **Biathlete** has *social media, points and stats*, which consist of *average speed* and *average shooting*. **Biathletes** can take part in competitions and their *Finish* place will be recorded in the **Leaderboard**. 

**Competition** has a *type, date,* and *time*, and is identified by a competitionID. A **Competition** has a **Stadium** *Holder.* The *Results* of a **Competition** are recorded in the **Leaderboard**.

**Leaderbord** is a table with all the results. It’s identified by a group of competition, biathlete, and place. Also, every record in this table is identified by a recordID.

A **Stadium** is identified by the trio of *name, city*, *and county*. A **Stadium** is located in a *country* and a *city*. It has its own **Staff**. A **Stadium** can hold **Competitions** and sell at least one **Ticket** to a **Viewer**.

A **Ticket** is sold by the **Stadium**. It has a *cost*, *sector*, a TicketID, and a TransactionID. A **Ticket** can be sold or not. Only one **Viewer** can use it.