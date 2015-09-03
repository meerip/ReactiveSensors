/*
 * Copyright (C) 2015 Piotr Wittchen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pwittchen.reactivesensors.library;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import rx.functions.Func1;

public final class ReactiveSensorEvent {
  private SensorEvent sensorEvent;
  private Sensor sensor;
  private int accuracy = -1;

  public ReactiveSensorEvent(SensorEvent sensorEvent) {
    this.sensorEvent = sensorEvent;
  }

  public ReactiveSensorEvent(Sensor sensor, int accuracy) {
    this.sensor = sensor;
    this.accuracy = accuracy;
  }

  public SensorEvent getSensorEvent() {
    return sensorEvent;
  }

  public Sensor getSensor() {
    return sensor;
  }

  public int getAccuracy() {
    return accuracy;
  }

  private boolean isSensorChanged() {
    return sensorEvent != null;
  }

  private boolean isAccuracyChanged() {
    return sensor != null && accuracy != -1;
  }

  /**
   * Predicate, which can be used in filter(...) method from RxJava
   * to filter all events in which sensor value has changed
   *
   * @return Func1<ReactiveSensorEvent, Boolean> predicate indicating if sensor value has changed
   */
  public static Func1<ReactiveSensorEvent, Boolean> filterSensorChanged() {
    return new Func1<ReactiveSensorEvent, Boolean>() {
      @Override public Boolean call(ReactiveSensorEvent reactiveSensorEvent) {
        return reactiveSensorEvent.isSensorChanged();
      }
    };
  }

  /**
   * Predicate, which can be used in filter(...) method from RxJava
   * to filter all events in which accuracy value has changed
   *
   * @return Func1<ReactiveSensorEvent, Boolean> predicate indicating if accuracy value has changed
   */
  public static Func1<ReactiveSensorEvent, Boolean> filterAccuracyChanged() {
    return new Func1<ReactiveSensorEvent, Boolean>() {
      @Override public Boolean call(ReactiveSensorEvent reactiveSensorEvent) {
        return reactiveSensorEvent.isAccuracyChanged();
      }
    };
  }
}
