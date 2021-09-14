 (ns minimal-api.boundary.db.todo
  (:require
   [minimal-api.boundary.db.core :as db]))

(defprotocol Todo
  (find-todos [db])
  (find-todo-by-id [db id])
  (create-todo! [db todo])
  (update-todo! [db id todo])
  (delete-todo! [db id]))

(extend-protocol Todo
  minimal_api.boundary.db.core.Boundary
  (find-todos [db]
    (db/select-all db :todo))
  (find-todo-by-id [db id]
                     (db/select-by-key db :todo id))
  (create-todo! [db todo]
                  (db/insert! db :todo "todo" todo))
  (update-todo! [db id todo]
                  (db/update-by-key! db :todo id todo))
  (delete-todo! [db id]
                  (db/delete-by-key! db :todo id)))