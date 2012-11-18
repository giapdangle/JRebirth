/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
 * Contact : sebastien.bordes@jrebirth.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.core.wave;

import javafx.util.Builder;

/**
 * The class <strong>WaveBuilder</strong>.
 * 
 * Base builder used to build a custom wave.
 * 
 * @author Sébastien Bordes
 * 
 * @param <B> the builder recusrive type
 */
public class WaveBuilder<B extends WaveBuilder<B>> implements Builder<WaveBase> {

    /** The internal properties mask. */
    private int setMask;

    /** The wave group of the wave to build. */
    private WaveGroup waveGroup;

    /** The wave type of the wave to build. */
    private WaveType waveType;

    /** The related class of the wave to build. */
    private Class<?> relatedClass;

    /** The wave bean class of the wave to build. */
    private Class<? extends WaveBean> waveBeanClass;

    /** The list of Wave Data. */
    private WaveData<?>[] waveData;

    /**
     * Create a WaveBuilder instance.
     * 
     * @return new instance of WaveBuilder
     */
    @SuppressWarnings("rawtypes")
    public static WaveBuilder<?> create() {
        return new WaveBuilder();
    }

    /**
     * Apply all wave properties.
     * 
     * @param paramWave the wave that need to be initialized with builder values
     */
    public void applyTo(final WaveBase paramWave) {
        // super.applyTo(paramWave);
        final int i = this.setMask;
        if ((i & 0x1) != 0) {
            paramWave.setWaveGroup(this.waveGroup);
        }
        if ((i & 0x2) != 0) {
            paramWave.setWaveType(this.waveType);
        }
        if ((i & 0x4) != 0) {
            paramWave.setRelatedClass(this.relatedClass);
        }
        if ((i & 0x8) != 0) {
            paramWave.setWaveBeanClass(this.waveBeanClass);
        }
        if ((i & 0x10) != 0) {
            paramWave.addDatas(this.waveData);
        }
    }

    /**
     * Define the wave group.
     * 
     * @param waveGroup the wave group to set
     * 
     * @return the builder
     */
    @SuppressWarnings("unchecked")
    public B waveGroup(final WaveGroup waveGroup) {
        this.waveGroup = waveGroup;
        this.setMask |= 1;
        return (B) this;
    }

    /**
     * Define the wave type.
     * 
     * @param waveType the wave type to set
     * 
     * @return the builder
     */
    @SuppressWarnings("unchecked")
    public B waveType(final WaveType waveType) {
        this.waveType = waveType;
        this.setMask |= 2;
        return (B) this;
    }

    /**
     * Define the related class.
     * 
     * @param relatedClass the related class to set
     * 
     * @return the builder
     */
    @SuppressWarnings("unchecked")
    public B relatedClass(final Class<?> relatedClass) {
        this.relatedClass = relatedClass;
        this.setMask |= 4;
        return (B) this;
    }

    /**
     * Define the wave bean class.
     * 
     * @param waveBeanClass the wave bean class to set
     * 
     * @return the builder
     */
    @SuppressWarnings("unchecked")
    public B waveBeanClass(final Class<? extends WaveBean> waveBeanClass) {
        this.waveBeanClass = waveBeanClass;
        this.setMask |= 8;
        return (B) this;
    }

    /**
     * Define the list of WaveData.
     * 
     * @param waveData the list of waveData to set
     * 
     * @return the builder
     */
    @SuppressWarnings("unchecked")
    public B data(final WaveData<?>... waveData) {
        this.waveData = waveData;
        this.setMask |= 16;
        return (B) this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaveBase build() {
        final WaveBase localWave = new WaveBase();
        applyTo(localWave);
        return localWave;
    }

}
