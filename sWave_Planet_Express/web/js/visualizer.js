function initAudioSystem() {
    sysAudioContext  = new AudioContext();
    sysAudioAnalyser = sysAudioContext.createAnalyser();
    sysAudioGain     = sysAudioContext.createGain();
    sysAudioGain.gain.value  = 0.5;
    sysAudioAnalyser.fftSize = 128;
    sysAudioAnalyser.smoothingTimeConstant = 0.6;
    sysAudioAnalyser.connect(sysAudioGain);
    sysAudioGain.connect(sysAudioContext.destination);
    addAudioSource($("player"));
    startAudioVisualization();
}

function addAudioSource(src) {
    var audio = sysAudioContext.createMediaElementSource(src);
    audio.connect(sysAudioAnalyser);
}

function startAudioVisualization() {
    scene  = new THREE.Scene();
    camera = new THREE.PerspectiveCamera(45,200/200,1,100);

    camera.position.set(0,0,50);
    camera.lookAt(scene.position);
    scene.add(camera);

    renderer = new THREE.WebGLRenderer({alpha:true, antialias:true});
	
    renderer.setSize(200,200);
    $("visualizer").appendChild(renderer.domElement);

    bars = new Array(new Array(40), new Array(40));
    
    var j = -40.0;
    for (var i = 0; i < 64; i++) {
        bars[1][i] = new THREE.Mesh(new THREE.PlaneBufferGeometry(0.1,0.5), new THREE.MeshBasicMaterial({color:0x2E84B1}));
        bars[0][i] = new THREE.Mesh(new THREE.PlaneBufferGeometry(0.1,0.5), new THREE.MeshBasicMaterial({color:0x838383}));
        bars[1][i].position.set(j,0,0);
        j += 0.7;
        bars[0][i].position.set(j,0,0);
        j += 0.7;
        scene.add(bars[1][i]);
        scene.add(bars[0][i]);
    }
    visualData = new Array(new Uint8Array(128), new Uint8Array(64));
    visualize();
}

function visualize() {
    sysAudioAnalyser.getByteTimeDomainData(visualData[0]);
    sysAudioAnalyser.getByteFrequencyData(visualData[1]);

    for (var i = 0; i < 64; i++) {
        bars[0][i].scale.y = (visualData[0][i] - 128) / 4;
        bars[1][i].scale.y = visualData[1][i] / 8;
    }
    
    setTimeout(requestAnimationFrame(visualize));
    renderer.render(scene,camera);
}