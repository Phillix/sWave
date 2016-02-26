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

//This code is eXastum-specific and should be made generic ASAP

function startAudioVisualization() {
    scene  = new THREE.Scene();
    camera = new THREE.PerspectiveCamera(45,1,1,100);

    camera.position.set(0,0,50);
    camera.lookAt(scene.position);
    scene.add(camera);

    renderer = new THREE.WebGLRenderer({alpha:true, antialias:true});
	
    renderer.setSize(200,200);
    $("audioPanelVisualizer").appendChild(renderer.domElement);

    bars = new Array(new Array(40), new Array(40));

    var j = 0;
    for (var i = 0; i < 40; i++) {
        bars[0][i] = new THREE.Mesh(new THREE.PlaneBufferGeometry(0.1,0.5), new THREE.MeshBasicMaterial({color:0xC22828}));
        //bars[1][i] = new THREE.Mesh(new THREE.PlaneBufferGeometry(0.1,0.5), new THREE.MeshBasicMaterial({color:0x838383}));
        alert(j);
        bars[0][i].position.set(j++,0,0);
        //bars[1][i].position.set((i - 19.5),0,0);
        scene.add(bars[0][i]);
        //scene.add(bars[1][i]);
    }

    visualize();
}

function visualize() {
    var visualData = new Array(new Uint8Array(128), new Uint8Array(64));

    sysAudioAnalyser.getByteTimeDomainData(visualData[0]);
    sysAudioAnalyser.getByteFrequencyData(visualData[1]);

    var barHeight = new Array(2);

    for (var i = 0; i < 40; i++) {
        //barHeight[0] = (visualData[0][i] - 128) / 2;
        //barHeight[1] = visualData[1][i] / 4;

        //if (!(barHeight[0] < 1)) bars[0][i].scale.y = barHeight[0];
        //else bars[0][i].scale.y = 0.1;

        //if (!(barHeight[1] < 1)) bars[1][i].scale.y = barHeight[1];
        //else bars[1][i].scale.y = 0.1;
        
        bars[0][i].scale.y = 20;
        //bars[1][i].scale.y = 20;
    }
	
    setTimeout(requestAnimationFrame(visualize));
    renderer.render(scene,camera);
}

function testAudio(x) {
    if (!x) {
        $("audioTestButton").innerHTML = "Stop Test";
        $("audioTestButton").setAttribute("onclick","testAudio(true)");
        testTrack.play();
    }
    else {
        testTrack.pause();
        testTrack.currentTime = 0;
        $("audioTestButton").innerHTML = "Test Audio";
        $("audioTestButton").setAttribute("onclick","testAudio()");
    }
}

//End of eXastum-specific code
