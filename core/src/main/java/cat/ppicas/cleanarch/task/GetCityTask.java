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

package cat.ppicas.cleanarch.task;

import cat.ppicas.cleanarch.domain.City;
import cat.ppicas.cleanarch.repository.CityRepository;

public class GetCityTask extends CancellableTask<City> {

    private String mId;

    private CityRepository mRepository;

    public GetCityTask(CityRepository repository, String id) {
        mId = id;
        mRepository = repository;
    }

    @Override
    protected City doExecute() throws Exception {
        return mRepository.getCity(mId);
    }
}
