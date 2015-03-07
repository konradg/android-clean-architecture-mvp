/*
 * Copyright (C) 2015 Pau Picas Sans <pau.picas@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package cat.ppicas.cleanarch.ui.presenter;

import android.os.Bundle;

import cat.ppicas.cleanarch.ui.display.Display;

public abstract class Presenter<T extends Display> {

    private T mDisplay;

    public T getDisplay() {
        return mDisplay;
    }

    public void bindDisplay(T display) {
        mDisplay = display;
    }

    public void unbindDisplay() {
        mDisplay = null;
    }

    public void saveState(Bundle state) {
    }

    public void restoreState(Bundle state) {
    }

    public void onDestroy() {
    }
}
