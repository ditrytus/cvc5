(set-logic ALL)
(set-info :status sat)
(declare-fun s () Real)
(declare-fun k () Real)
(assert (>= (* s k) 1))
(check-sat)
