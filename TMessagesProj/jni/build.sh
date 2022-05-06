test -d "$ANDROID_SDK_ROOT" && ! test -f "$NDK" && export NDK="$ANDROID_SDK_ROOT/ndk/$(ls "$ANDROID_SDK_ROOT/ndk" | tail -1)"
export NINJA_PATH="$(command -v ninja)"
git submodule update --init
git submodule foreach git reset --hard && git clean -fd
./build_ffmpeg_clang.sh
./patch_ffmpeg.sh
./patch_boringssl.sh
./build_boringssl.sh
