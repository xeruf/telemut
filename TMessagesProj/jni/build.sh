# prepare environment
if ! test -f "$NDK"; then
  ndks="${ANDROID_HOME:-$ANDROID_SDK_ROOT}/ndk"
  test -d "$ndks" && export NDK="$ndks/$(ls "$ndks" | tail -1)"
fi
export NINJA_PATH="$(command -v ninja)"

# prepare submodules
cd "$(dirname $0)"
git submodule foreach git clean -xdf
git submodule update --init --force

# build
./build_ffmpeg_clang.sh
./patch_ffmpeg.sh
./patch_boringssl.sh
./build_boringssl.sh

# Examples for invoking the scripts individually
# NDK=/opt/android/sdk/ndk/22.0.7026061 ./build_ffmpeg_clang.sh
# NINJA_PATH=$(command -v ninja) NDK=/opt/android/sdk/ndk/22.0.7026061 ./build_boringssl.sh