drop view if exists game_view;
create or replace view game_view
as (select g.id,
           gym.name as gym,
           g.date,
           g.entry_time,
           g.start_time,
           owner.name as owner,
           guest.name as guest
    from game g
    left join gym on gym.id=g.gym_id
    left join team owner on owner.id=g.owner_id
    left join team guest on guest.id=g.guest_id);