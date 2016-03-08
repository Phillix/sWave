/*
    Copyright 2015, 2016 Brian Millar
    This file is part of Macgril.
    Macgril is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    Macgril is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with eXastum.  If not, see <http://www.gnu.org/licenses/>
*/

function initAudioSystem() {
    sysAudioContext  = new AudioContext();
    sysAudioAnalyser = sysAudioContext.createAnalyser();
    sysAudioGain     = sysAudioContext.createGain();
    sysAudioGain.gain.value  = 0.5;
    sysAudioAnalyser.fftSize = 128;
    sysAudioAnalyser.smoothingTimeConstant = 0.4;
    sysAudioAnalyser.connect(sysAudioGain);
    sysAudioGain.connect(sysAudioContext.destination);
}

function addAudioSource(src) {
    (sysAudioContext.createMediaElementSource(src)).connect(sysAudioAnalyser);
}

function setSysVol(level) {
    sysAudioGain.gain.value = (level + 120) * 0.004166667;
}


function startAudioVisualization(element, width, height, color1, color2, func) {
    scene  = new THREE.Scene();
    camera = new THREE.PerspectiveCamera(45, width / height, 1, 100);

    camera.position.set(0,0,50);
    camera.lookAt(scene.position);
    scene.add(camera);

    renderer = new THREE.WebGLRenderer({alpha:true, antialias:true});

    renderer.setSize(width, height);
    $(element).appendChild(renderer.domElement);

    bars = new Array(new Array(40), new Array(40));

    var j = -40.0;
    for (var i = 0; i < 64; i++) {
        col1 = "0x" + color1;
        col2 = "0x" + color2;
        bars[1][i] = new THREE.Mesh(new THREE.PlaneBufferGeometry(0.1,0.5), new THREE.MeshBasicMaterial({color:col1}));
        bars[0][i] = new THREE.Mesh(new THREE.PlaneBufferGeometry(0.1,0.5), new THREE.MeshBasicMaterial({color:col2}));
        bars[1][i].position.set(j,0,0);
        j += 0.7;
        bars[0][i].position.set(j,0,0);
        j += 0.7;
        scene.add(bars[1][i]);
        scene.add(bars[0][i]);
    }
    visualData = new Array(new Uint8Array(128), new Uint8Array(64));
    visualize(func);
}

function visualize(func) {
    sysAudioAnalyser.getByteTimeDomainData(visualData[0]);
    sysAudioAnalyser.getByteFrequencyData(visualData[1]);
    for (var i = 0; i < 64; i++) {
        bars[0][i].scale.y = (visualData[0][i] - 128) / 4;
        bars[1][i].scale.y = visualData[1][i] / 8;
    }
    exec(func);
    setTimeout(requestAnimationFrame(visualize));
    renderer.render(scene, camera);
}