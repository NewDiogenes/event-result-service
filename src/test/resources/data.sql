INSERT INTO Team (id, short_name, long_name, abbreviated_name)
VALUES ('pakistan','pakistan','Pakistan', 'PAK'),
       ('england','england','England', 'ENG'),
       ('west-indies','west-indies','West Indies', 'WIN');

INSERT INTO Competition (uid, id, competition_date, name)
VALUES ('ljhcvjgfx', 'jqpiuhojhb', '2020-8-5', 'England vs Pakistan 1st Test'),
       ('ljgougcigc', 'hgfiugvb', '2020-8-17', 'England vs Pakistan 2nd Test'),
       ('sdfghlmnbvcxdf', 'xcvbnkurf', '2020-7-8', 'England vs West Indies 1st Test'),
       ('jtdfdxjhyg', 'ouyfutrs', '2020-7-16', 'England vs West Indies 2nd Test'),
       ('wertyuiokbv', 'sdfghjklbv', '2020-7-28', 'England vs West Indies 3rd Test');

INSERT INTO Competitor (id, score, home_away, is_winner, team_id)
VALUES (1, 326, 'away', false, 'pakistan'),
       (2, 219, 'home', true, 'england'),
       (3, 236, 'away', true, 'pakistan'),
       (4, 110, 'home', false, 'england'),
       (5, 318, 'away', true, 'west-indies'),
       (6, 204, 'home', false, 'england'),
       (7, 287, 'away', false, 'west-indies'),
       (8, 469, 'home', true, 'england'),
       (9, 197, 'away', false, 'west-indies'),
       (10, 369, 'home', true, 'england');

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