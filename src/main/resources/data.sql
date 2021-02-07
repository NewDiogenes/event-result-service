INSERT INTO Team (uid, id, name)
VALUES ('pakistan','pakistan','Pakistan'),
       ('england','england','England'),
       ('west-indies','west-indies','West Indies');

INSERT INTO Competition (uid, id, competition_date, name)
VALUES ('ljhcvjgfx', 'jqpiuhojhb', '2020-8-5', 'England vs Pakistan 1st Test'),
       ('ljgougcigc', 'hgfiugvb', '2020-8-17', 'England vs Pakistan 2nd Test'),
       ('sdfghlmnbvcxdf', 'xcvbnkurf', '2020-7-8', 'England vs West Indies 1st Test'),
       ('jtdfdxjhyg', 'ouyfutrs', '2020-7-16', 'England vs West Indies 2nd Test'),
       ('wertyuiokbv', 'sdfghjklbv', '2020-7-28', 'England vs West Indies 3rd Test');

INSERT INTO Competitor (id, type, score, home_away, is_winner, team_id)
VALUES (1, 'team', 326, 'away', false, 'pakistan'),
       (2, 'team', 219, 'home', true, 'england'),
       (3, 'team', 236, 'away', true, 'pakistan'),
       (4, 'team', 110, 'home', false, 'england'),
       (5, 'team', 318, 'away', true, 'west-indies'),
       (6, 'team', 204, 'home', false, 'england'),
       (7, 'team', 287, 'away', false, 'west-indies'),
       (8, 'team', 469, 'home', true, 'england'),
       (9, 'team', 197, 'away', false, 'west-indies'),
       (10, 'team', 369, 'home', true, 'england');

INSERT INTO Competition_Competitors (competition_uid, competitors_id)
VALUES ('ljhcvjgfx', 1),
       ('ljhcvjgfx', 2),
       ('ljgougcigc', 3),
       ('ljgougcigc', 4),
       ('sdfghlmnbvcxdf', 5),
       ('sdfghlmnbvcxdf', 6),
       ('jtdfdxjhyg', 7),
       ('jtdfdxjhyg', 8),
       ('wertyuiokbv', 9),
       ('wertyuiokbv', 10);