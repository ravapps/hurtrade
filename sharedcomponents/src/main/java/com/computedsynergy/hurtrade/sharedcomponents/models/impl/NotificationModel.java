/*
 * Copyright 2016 Faisal Thaheem <faisal.ajmal@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.computedsynergy.hurtrade.sharedcomponents.models.impl;

import com.computedsynergy.hurtrade.sharedcomponents.models.interfaces.INotificationModel;
import com.computedsynergy.hurtrade.sharedcomponents.models.pojos.CoverAccount;
import org.sql2o.Connection;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by faisal.t on 7/12/2017.
 */
public class NotificationModel extends ModelBase implements INotificationModel {

    @Override
    public void saveNotification(int user_id, int office_id, String notification) {

        String insertSql =
                "INSERT INTO notifications " +
                        "(user_id, office_id, created, notification) " +
                        "VALUES(:user_id, :office_id, current_timestamp, :notification) ";

        try (Connection con = sql2o.open()) {

            con.createQuery(insertSql)
                    .addParameter("user_id", user_id)
                    .addParameter("office_id", office_id)
                    .addParameter("notification", notification)
                    .executeUpdate();

        }catch(Exception ex){
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
